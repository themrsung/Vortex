package oasis.vortex.scheduler.tick;

import oasis.vortex.scheduler.AbstractScheduler;
import oasis.vortex.task.tick.TickTask;
import org.joda.time.Duration;

import javax.annotation.Nonnull;

/**
 * <h2>TickScheduler</h2>
 * <p>Handles the scheduling of ticks.</p>
 */
public final class TickScheduler extends AbstractScheduler<TickTask> {
    @Nonnull
    private static final Duration interval = new Duration(50);

    @Nonnull
    @Override
    public Duration getInterval() {
        return interval;
    }
}
