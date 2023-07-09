package oasis.vortex.tickable.movement;

import oasis.vortex.Vortex;
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
        Vortex.getState().getWorlds().forEach(w -> {
            w.getObjects().forEach(o -> {
                o.setLocation(o.getLocation().plusVector(o.getVector()));

                System.out.println("Tick took " + delta.getMillis() + "ms to execute.");
                System.out.println("Current Y of object is: " + o.getLocation().y());
            });
        });
    }
}
