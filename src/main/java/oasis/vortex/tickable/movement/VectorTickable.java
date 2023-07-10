package oasis.vortex.tickable.movement;

import oasis.vortex.Vortex;
import oasis.vortex.object.ImmovableObject;
import oasis.vortex.tickable.Tickable;
import oasis.vortex.util.collection.set.BetterSet;
import oasis.vortex.util.physics.TriLocation;
import org.joda.time.Duration;

import javax.annotation.Nonnull;

public final class VectorTickable implements Tickable {
    @Override
    public void tick(@Nonnull Duration delta) {
        Vortex.getState().getWorlds().forEach(w -> w.getObjects().forEach(o -> {
            if (o.obeysPhysics()) {
                // Handle gravity
                final double accelerationFromGravity = w.getGravity() / 1000 * delta.getMillis();
                o.setVector(o.getVector().plusY(-accelerationFromGravity));

                // Terminal velocity
                final double crossSection = o.getVolume().getCrossSectionXZ();
                final double fluidDensity = w.getAirDensity();
                final double gravity = w.getGravity();

                final double terminalVelocity = Math.sqrt(
                        (2 * o.getMass().valueKilograms() * gravity) /
                                (fluidDensity * crossSection * o.getDragCoefficient())
                );

                BetterSet<ImmovableObject> immovableObjects = w.getObjects().filter(ImmovableObject.class);
                for (ImmovableObject im : immovableObjects) {
                    final TriLocation tl = im.getTriLocation().toBuilder().volume(im.getVolume().setY(im.getVolume().y() + 0.5)).build();
                    // This is a bodged solution for debugging. Please re-implement this. (note to future self)
                    if (tl.overlaps(o.getTriLocation())) {
                        o.setVector(o.getVector().plusY(accelerationFromGravity)); // Cancel gravity if object is on top of an immovable object.
                    }
                }
                if (o.getVector().getVelocity() > terminalVelocity) {
                    double modifier = terminalVelocity / o.getVector().getVelocity();
                    o.setVector(o.getVector().modifyAll(modifier));
                }
            }
        }));
    }
}
