package oasis.vortex.task;

import javax.annotation.Nonnull;
import java.time.Duration;

/**
 * <h2>Task</h2>
 * <p>
 * A task is a superinterface for all Vortex tasks.
 * Tasks can be scheduled to be performed regularly, or once with a specified delay.
 * </p>
 */
public interface Task {
    /**
     * Executes this task.
     *
     * @param delta Delta between ths last call and now.
     */
    void execute(@Nonnull Duration delta);
}
