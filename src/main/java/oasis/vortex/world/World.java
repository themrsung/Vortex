package oasis.vortex.world;

import oasis.vortex.object.actor.Actor;
import oasis.vortex.util.collection.set.BetterSet;

import javax.annotation.Nonnull;

/**
 * <h2>World</h2>
 * <p>
 * A world represents an in-game level.
 * There can be multiple worlds in one game state.
 * </p>
 */
public interface World {
    /**
     * Gets a set of actors in this world.
     * @return {@link BetterSet}
     */
    @Nonnull
    BetterSet<Actor> getActors();

    /**
     * Adds an actor to this world.
     * @param actor Actor to add
     */
    void addActor(@Nonnull Actor actor);

    /**
     * Removes an actor from this world.
     * @param actor Actor to remove
     */
    void removeActor(@Nonnull Actor actor);
}
