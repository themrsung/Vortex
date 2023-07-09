package oasis.vortex.world;

import oasis.vortex.object.Object;
import oasis.vortex.tickable.Tickable;
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
    //
    // Objects
    //

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
     * @param object Object to add
     */
    void addObject(@Nonnull Object object);

    /**
     * Removes an object from this world.
     *
     * @param object Object to remove
     */
    void removeObject(@Nonnull Object object);

    //
    // Physics
    //

    /**
     * The default gravity.
     */
    double DEFAULT_GRAVITY = 9.807d;

    /**
     * Gets the gravity of this world, denoted in meters per second squared.
     *
     * @return Gravity
     */
    double getGravity();

    /**
     * Sets the gravity of this world, denoted in meters per second squared.
     *
     * @param gravity Gravity
     */
    void setGravity(double gravity);
}
