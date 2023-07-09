package oasis.vortex.event;

import javax.annotation.Nullable;

/**
 * <h2>PreemptiveEvent</h2>
 * <p>
 *     A preemptive event is cancellable, and can be put anywhere in an event chain.
 *     Preemptive events can either succeed or fail.
 *     The successive events will only be called upon succession of this event.
 * </p>
 * @see Event
 */
public interface PreemptiveEvent extends Event {
    /**
     * Gets the successive event of this event.
     * The successor will be called upon the succession of this event.
     *
     * @return Successor
     */
    @Nullable
    Event getSuccessor();

    abstract class AbstractPreemptiveEvent extends AbstractEvent implements PreemptiveEvent {
        public AbstractPreemptiveEvent() {
            this.successor = null;
        }

        public AbstractPreemptiveEvent(@Nullable Event successor) {
            this.successor = successor;
        }

        @Nullable
        final Event successor;

        @Override
        @Nullable
        public Event getSuccessor() {
            return successor;
        }
    }
}
