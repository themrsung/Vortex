package oasis.vortex;

import oasis.vortex.event.dummy.DummyEvent;
import oasis.vortex.listener.dummy.DummyListener;
import oasis.vortex.scheduler.event.EventScheduler;
import oasis.vortex.scheduler.tick.TickScheduler;
import oasis.vortex.state.State;
import oasis.vortex.state.VortexState;
import oasis.vortex.task.event.EventTask;
import oasis.vortex.task.tick.TickTask;

import javax.annotation.Nonnull;

/**
 * <h2>Vortex</h2>
 * <p>The main class of Vortex.</p>
 */
public final class Vortex {
    //
    // Methods
    //

    /**
     * Gets the running state of Vortex.
     * @return {@link State}
     */
    @Nonnull
    public static State getState() {
        return state;
    }

    /**
     * Gets the tick task of Vortex.
     * @return {@link TickTask}
     */
    @Nonnull
    public static TickTask getTickTask() {
        return tickTask;
    }

    /**
     * Gets the event task of Vortex.
     * @return {@link EventTask}
     */
    @Nonnull
    public static EventTask getEventTask() {
        return eventTask;
    }

    //
    // Main
    //

    /**
     * Called on startup.
     * @param args Arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello world!");

        tickScheduler.registerTask(tickTask);
        tickScheduler.start();

        eventScheduler.registerTask(eventTask);
        eventScheduler.start();

        eventTask.registerListener(new DummyListener());

        eventTask.callEvent(new DummyEvent());
    }

    //
    // Static fields
    //

    // State
    @Nonnull
    private static final State state = new VortexState();

    // Tasks
    @Nonnull
    private static final TickTask tickTask = new TickTask();
    @Nonnull
    private static final EventTask eventTask = new EventTask();

    // Schedulers
    @Nonnull
    private static final TickScheduler tickScheduler = new TickScheduler();
    @Nonnull
    private static final EventScheduler eventScheduler = new EventScheduler();

}