package oasis.vortex.scheduler.event;

import oasis.vortex.scheduler.AbstractScheduler;
import oasis.vortex.task.event.EventTask;
import org.joda.time.Duration;

import javax.annotation.Nonnull;

/**
 * <h2>EventScheduler</h2>
 * <p>Handles the scheduling of events.</p>
 */
public final class EventScheduler extends AbstractScheduler<EventTask> {
    @Nonnull
    private static final Duration interval = new Duration(100);

    @Nonnull
    @Override
    public Duration getInterval() {
        return interval;
    }
}
