package oasis.vortex.scheduler;

import oasis.vortex.task.Task;
import oasis.vortex.util.collection.list.BetterArrayList;
import oasis.vortex.util.collection.list.BetterList;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import javax.annotation.Nonnull;

/**
 * <h2>AbstractScheduler</h2>
 * <p>A superclass for easier implementation of {@link Scheduler}.</p>
 */
public abstract class AbstractScheduler<T extends Task> implements Scheduler<T> {
    @Override
    public void registerTask(@Nonnull T task) {
        tasks.add(task);
    }

    @Override
    public void unregisterTask(@Nonnull T task) {
        tasks.remove(task);
    }

    @Override
    public void clearTasks() {
        tasks.clear();
    }

    @Nonnull
    @Override
    public BetterList<T> getTasks() {
        return new BetterArrayList<>(tasks);
    }

    private final BetterList<T> tasks;
    @Nonnull
    private DateTime lastLoop = DateTime.now();
    private boolean active = false;

    @SuppressWarnings("BusyWait")
    @Nonnull
    private final Thread thread = new Thread(() -> {
        while (active) {
            final Duration delta = new Duration(lastLoop, DateTime.now());
            getTasks().forEach(t -> t.execute(delta));
            lastLoop = DateTime.now();

            try {
                Thread.sleep(getInterval().getMillis());
            } catch (InterruptedException e) {
                stop();
            }
        }
    });

    @Override
    public void start() {
        active = true;
        thread.start();
    }

    @Override
    public void stop() {
        active = false;
    }

    public AbstractScheduler() {
        this.tasks = new BetterArrayList<>();
    }
}
