package oasis.vortex.world;

import oasis.vortex.object.Object;
import oasis.vortex.tickable.Tickable;
import oasis.vortex.util.collection.set.BetterSet;
import oasis.vortex.util.meta.Unique;
import oasis.vortex.util.string.BetterString;

import javax.annotation.Nonnull;
import javax.validation.constraints.Positive;

/**
 * <h2>World</h2>
 * <p>
 * A world represents an in-game level.
 * There can be multiple worlds in one game state.
 * </p>
 */
public interface World extends Unique, Tickable {
    //
    // Identification
    //

    /**
     * Gets the name of this world.
     * @return Name
     */
    @Nonnull
    BetterString getName();

    /**
     * Sets the name of this world.
     * @param name Name
     */
    void setName(@Nonnull BetterString name);

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
     * The default air density.
     */
    double DEFAULT_AIR_DENSITY = 1.204d;

    /**
     * Gets the gravity of this world, denoted in meters per second squared.
     *
     * @return Gravity
     */
    double getGravity();

    /**
     * Gets the air density of this world, denoted in kilograms per cubic meter.
     *
     * @return Air density in kg/m3
     */
    @Positive
    double getAirDensity();

    /**
     * Sets the gravity of this world, denoted in meters per second squared.
     *
     * @param gravity Gravity
     */
    void setGravity(double gravity);

    /**
     * Sets the air density of this world, denoted in kilograms per cubic meter.
     * Density cannot be zero, as it will break physics calculations.
     * For an air-less, world set this to {@link Double#MIN_VALUE}.
     *
     * @param density Density in kg/m3
     */
    void setAirDensity(@Positive double density);
}
