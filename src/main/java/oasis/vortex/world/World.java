package oasis.vortex.world;

import oasis.vortex.object.Object;
import oasis.vortex.task.tick.Tickable;
import oasis.vortex.util.collection.set.BetterSet;
import oasis.vortex.util.meta.Unique;

import javax.annotation.Nonnull;

/**
 * <h2>World</h2>
 * <p>
 * A world represents an in-game level.
 * There can be multiple worlds in one game state.
 * </p>
 */
public interface World extends Unique, Tickable {
    /**
     * Gets a set of objects in this world.
     *
     * @return {@link BetterSet} of objects
     */
    @Nonnull
    BetterSet<Object> getObjects();

    /**
     * Adds an object to this world.
     *
     * @param actor Object to add
     */
    void addObject(@Nonnull Object actor);

    /**
     * Removes an object from this world.
     *
     * @param actor Object to remove
     */
    void removeObject(@Nonnull Object actor);
}
