package oasis.vortex.event;

import org.joda.time.DateTime;

import javax.annotation.Nonnull;

/**
 * <h2>Event</h2>
 * <p>
 *     An event is called either before an action in order to execute it,
 *     or after the action to notify other classes of its execution.
 * </p>
 * <p>
 *     Preemptive events are called before the execution of an action.
 *     They can be cancelled and can have a successive event.
 * </p>
 * <p>
 *     Permissive events are called after the execution of an action.
 *     They cannot be cancelled and do not have a successive event.
 * </p>
 *
 * @see PreemptiveEvent
 * @see PermissiveEvent
 */
public interface Event {
    /**
     * Gets the exact moment this event was constructed.
     * @return {@link DateTime}
     */
    @Nonnull
    DateTime getDate();
}

abstract class AbstractEvent implements Event {
    public AbstractEvent() {
        this.date = DateTime.now();
    }

    @Nonnull
    private final DateTime date;

    @Override
    @Nonnull
    public DateTime getDate() {
        return date;
    }
}