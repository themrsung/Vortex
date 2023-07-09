package oasis.vortex.event;

import javax.annotation.Nullable;

/**
 * <h2>PreemptiveEvent</h2>
 * <p>
 * A preemptive event is cancellable, and can be put anywhere in an event chain.
 * Preemptive events can either succeed or fail.
 * The successive events will only be called upon succession of this event.
 * </p>
 *
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

    /**
     * Gets whether this event has been cancelled before handling.
     *
     * @return {@code true} if this event has been cancelled
     */
    boolean isCancelled();

    /**
     * Sets whether this event has been cancelled before handling.
     *
     * @param cancelled Whether this event has been cancelled
     */
    void setCancelled(boolean cancelled);

    abstract class AbstractPreemptiveEvent extends AbstractEvent implements PreemptiveEvent {
        public AbstractPreemptiveEvent() {
            this.successor = null;
            this.cancelled = false;
        }

        public AbstractPreemptiveEvent(@Nullable Event successor) {
            this.successor = successor;
            this.cancelled = false;
        }

        @Nullable
        private final Event successor;
        private boolean cancelled;

        @Override
        @Nullable
        public Event getSuccessor() {
            return successor;
        }

        @Override
        public boolean isCancelled() {
            return cancelled;
        }

        @Override
        public void setCancelled(boolean cancelled) {
            this.cancelled = cancelled;
        }
    }
}
