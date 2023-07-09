package oasis.vortex.task.tick;

import oasis.vortex.Vortex;
import oasis.vortex.task.Task;
import oasis.vortex.util.collection.list.BetterArrayList;
import oasis.vortex.util.collection.list.BetterList;
import org.joda.time.Duration;

import javax.annotation.Nonnull;

/**
 * <h2>TickTask</h2>
 * <p>
 * Handles the ticking of every {@link Tickable}.
 * Worlds and objects contained within the game state are automatically called,
 * and do not require registration.
 * </p>
 * <p>
 * Outside tickables require registration.
 * </p>
 */
public final class TickTask implements Task {
    public TickTask() {
        this.tickables = new BetterArrayList<>();
    }

    @Override
    public void execute(@Nonnull Duration delta) {
        // Tick the game state
        Vortex.getState().tick(delta);

        // Tick registered tickables
        for (Tickable tickable : tickables) tickable.tick(delta);
    }

    /**
     * Registers a tickable to be called every tick.
     *
     * @param tickable Tickable to register
     */
    public void registerTickable(@Nonnull Tickable tickable) {
        tickables.add(tickable);
    }

    /**
     * Unregisters a tickable. The tickable will no longer be called every tick.
     *
     * @param tickable Tickable to unregister
     */
    public void unregisterTickable(@Nonnull Tickable tickable) {
        tickables.remove(tickable);
    }

    @Nonnull
    private final BetterList<Tickable> tickables;

}
