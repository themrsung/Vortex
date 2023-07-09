package oasis.vortex.task.tick;

import javax.annotation.Nonnull;
import java.time.Duration;

/**
 * <h2>Tickable</h2>
 * <p>
 * A superinterfaces for classes that require to be called every tick.
 * </p>
 */
public interface Tickable {
    /**
     * Called every tick.
     *
     * @param delta Actual delta between the last tick and this one
     */
    void tick(@Nonnull Duration delta);
}
