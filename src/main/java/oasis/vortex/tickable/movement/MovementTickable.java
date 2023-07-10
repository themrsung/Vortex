package oasis.vortex.tickable.movement;

import oasis.vortex.Vortex;
import oasis.vortex.object.ImmovableObject;
import oasis.vortex.tickable.Tickable;
import org.joda.time.Duration;

import javax.annotation.Nonnull;

/**
 * <h2>MovementTickable</h2>
 * <p>Handles the movement of objects.</p>
 */
public final class MovementTickable implements Tickable {
    @Override
    public void tick(@Nonnull Duration delta) {
        Vortex.getState().getWorlds().forEach(w -> w.getObjects().forEach(o -> {
            o.setLocation(o.getLocation().plusVector(o.getVector().modifyAll(1.0 / 1000 * delta.getMillis())));

            if (!(o instanceof ImmovableObject)) {
                System.out.println("Object's location: " + o.getLocation().x() + ", " + o.getLocation().y() + ", " + o.getLocation().z());
            }
        }));
    }
}
