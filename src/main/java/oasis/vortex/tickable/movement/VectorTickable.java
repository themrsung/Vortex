package oasis.vortex.tickable.movement;

import oasis.vortex.Vortex;
import oasis.vortex.tickable.Tickable;
import org.joda.time.Duration;

import javax.annotation.Nonnull;

public final class VectorTickable implements Tickable {
    @Override
    public void tick(@Nonnull Duration delta) {
        Vortex.getState().getWorlds().forEach(w -> {
            w.getObjects().forEach(o -> {
                if (o.obeysPhysics()) {
                    // Handle gravity
                    final double accelerationFromGravity = w.getGravity() / 1000 * delta.getMillis();
                    o.setVector(o.getVector().plusY(-accelerationFromGravity));

                    // Terminal velocity
                    final double crossSection = o.getVolume().getCrossSectionXZ();
                    final double fluidDensity = 1.204;
                    final double gravity = w.getGravity();

                    final double terminalVelocity = Math.sqrt(
                            (2 * o.getMass().valueKilograms() * gravity) /
                                    (fluidDensity * crossSection * o.getDragCoefficient())
                    );

                    if (o.getVector().getVelocity() > terminalVelocity) {
                        double modifier = terminalVelocity / o.getVector().getVelocity();
                        o.setVector(o.getVector().modifyAll(modifier));
                    }
                }
            });
        });
    }
}
