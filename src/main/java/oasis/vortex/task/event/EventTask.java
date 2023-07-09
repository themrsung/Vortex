package oasis.vortex.task.event;

import oasis.vortex.event.Event;
import oasis.vortex.listener.Listener;
import oasis.vortex.task.Task;
import oasis.vortex.util.collection.list.BetterArrayList;
import oasis.vortex.util.collection.list.BetterList;
import org.joda.time.Duration;

import javax.annotation.Nonnull;

/**
 * <h2>EventTask</h2>
 * <p>Handles the calling and processing of {@link Event}s.</p>
 */
public final class EventTask implements Task {
    //
    // Events
    //

    /**
     * Adds an event to the queue.
     *
     * @param event Event to add
     */
    public void callEvent(@Nonnull Event event) {
        events.add(event);
    }

    //
    // Listeners
    //

    /**
     * Registers a listener.
     *
     * @param listener Listener to register
     */
    public void registerListener(@Nonnull Listener<?> listener) {
        listeners.add(listener);
    }

    /**
     * Registers multiple listeners.
     *
     * @param listeners List of listeners
     */
    public void registerListeners(@Nonnull BetterList<Listener<?>> listeners) {
        listeners.forEach(this::registerListener);
    }

    /**
     * Unregisters a listener.
     *
     * @param listener Listener to unregister
     */
    public void unregisterListener(@Nonnull Listener<?> listener) {
        listeners.remove(listener);
    }

    /**
     * Unregisters multiple listeners.
     *
     * @param listeners List of listeners
     */
    public void unregisterListeners(@Nonnull BetterList<Listener<?>> listeners) {
        listeners.forEach(this::unregisterListener);
    }

    //
    // Implementation
    //

    @Override
    public void execute(@Nonnull Duration delta) {
        while (events.size() != 0) {
            final Event first = events.get(0);
            listeners.forEach(l -> l.handleEvent(first));
            events.remove(0);
        }
    }

    private final BetterList<Listener<?>> listeners;
    private final BetterList<Event> events;

    public EventTask() {
        this.listeners = new BetterArrayList<>();
        this.events = new BetterArrayList<>();
    }

    public EventTask(@Nonnull BetterList<Listener<?>> listeners) {
        this.listeners = listeners;
        this.events = new BetterArrayList<>();
    }

}
