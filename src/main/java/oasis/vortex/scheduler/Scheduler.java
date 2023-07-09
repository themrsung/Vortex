package oasis.vortex.scheduler;

import oasis.vortex.task.Task;
import oasis.vortex.util.collection.list.BetterList;
import org.joda.time.Duration;

import javax.annotation.Nonnull;

/**
 * <h2>Scheduler</h2>
 * <p>
 * A superinterface for task schedulers.
 * Schedulers can accept tasks as registries,
 * and handle the regular calling of given task.
 * </p>
 */
public interface Scheduler<T extends Task> {
    /**
     * Registers a task to be called regularly by this scheduler.
     *
     * @param task Task to register
     */
    void registerTask(@Nonnull T task);

    /**
     * Unregisters a task from this scheduler.
     *
     * @param task Task to unregister
     */
    void unregisterTask(@Nonnull T task);

    /**
     * Unregisters every task this scheduler is handling.
     */
    void clearTasks();

    /**
     * Gets the interval of this scheduler.
     * Every registered task is called regularly with this interval.
     *
     * @return Interval
     */
    @Nonnull
    Duration getInterval();

    /**
     * Gets a list of all registered tasks of this scheduler.
     *
     * @return List of registered tasks
     */
    @Nonnull
    BetterList<T> getTasks();

    /**
     * Starts this scheduler.
     */
    void start();

    /**
     * Stops this scheduler.
     */
    void stop();
}
