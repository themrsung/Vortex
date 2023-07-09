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
                    final double gravity = ((double) 1 / (1000 - delta.getMillis())) * Math.sqrt(w.getGravity());
                    o.setVector(o.getVector().plusY(-gravity));
                }
            });
        });
    }
}
