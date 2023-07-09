package oasis.vortex.listener;

import oasis.vortex.event.Event;

import javax.annotation.Nonnull;

/**
 * <h2>Listener</h2>
 * <p>A listener subscribes to {@link Event}, and is notified when an event is called.</p>
 */
public interface Listener<E extends Event> {
    /**
     * Called when the event occurs.
     * @param event Event that occurred
     */
    void handle(@Nonnull E event);

    /**
     * Gets the class of event this listener subscribes to.
     * @return Class of event
     */
    @Nonnull
    Class<E> getEventClass();

    /**
     * Called from event task.
     * Handles internal listener processing.
     *
     * @param event Event called
     */
    default void handleEvent(@Nonnull Event event) {
        if (!getEventClass().isInstance(event)) return;
        handle(getEventClass().cast(event));
    }
}
