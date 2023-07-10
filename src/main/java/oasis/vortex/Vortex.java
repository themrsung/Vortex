package oasis.vortex;

import oasis.vortex.event.dummy.DummyEvent;
import oasis.vortex.listener.dummy.DummyListener;
import oasis.vortex.listener.physics.CollisionListener;
import oasis.vortex.object.DummyObject;
import oasis.vortex.object.ImmovableObject;
import oasis.vortex.object.Object;
import oasis.vortex.scheduler.event.EventScheduler;
import oasis.vortex.scheduler.tick.TickScheduler;
import oasis.vortex.state.State;
import oasis.vortex.state.VortexState;
import oasis.vortex.task.event.EventTask;
import oasis.vortex.task.tick.TickTask;
import oasis.vortex.tickable.movement.MovementTickable;
import oasis.vortex.tickable.movement.VectorTickable;
import oasis.vortex.util.physics.Mass;
import oasis.vortex.util.physics.Volume;
import oasis.vortex.util.string.Text;
import oasis.vortex.world.RealisticWorld;
import oasis.vortex.world.World;

import javax.annotation.Nonnull;
import java.text.NumberFormat;
import java.util.UUID;

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
     *
     * @return {@link State}
     */
    @Nonnull
    public static State getState() {
        return state;
    }

    /**
     * Gets the tick task of Vortex.
     *
     * @return {@link TickTask}
     */
    @Nonnull
    public static TickTask getTickTask() {
        return tickTask;
    }

    /**
     * Gets the event task of Vortex.
     *
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
     *
     * @param args Arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Mass test = new Mass(1, Mass.Unit.TON).addValue(100, Mass.Unit.KILOGRAM);
        System.out.println(NumberFormat.getInstance().format(test.value()));

        // Register tasks
        tickScheduler.registerTask(tickTask);
        tickScheduler.start();

        eventScheduler.registerTask(eventTask);
        eventScheduler.start();

        // Register tickables
        tickTask.registerTickable(new MovementTickable());
        tickTask.registerTickable(new VectorTickable());

        // Register listeners
        eventTask.registerListener(new DummyListener());
        eventTask.registerListener(new CollisionListener());


        eventTask.callEvent(new DummyEvent());

        // Testing out gravity

        final World world = new RealisticWorld(UUID.randomUUID(), new Text("Realistic World"));
        state.addWorld(world);

        final Object human = new DummyObject(world);
        human.setMass(new Mass(70, Mass.Unit.KILOGRAM));
        human.setLocation(human.getLocation().plusY(555)); // The height of Lotte Tower in Seoul, Korea
        human.setDragCoefficient(1.0);
        human.setVolume(new Volume(0.4, 1.7, 0.4));

//        testObject.setVector(testObject.getVector().setX(1.42));

        world.addObject(human);

        final Object ground = ImmovableObject.ground(world, 0);
        world.addObject(ground);

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