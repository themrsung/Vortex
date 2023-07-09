package oasis.vortex.object;

import oasis.vortex.tickable.Tickable;
import oasis.vortex.util.meta.Unique;
import oasis.vortex.util.physics.Location;
import oasis.vortex.util.physics.Mass;
import oasis.vortex.util.physics.Vector;
import oasis.vortex.util.physics.Volume;
import oasis.vortex.world.World;

import javax.annotation.Nonnull;

/**
 * <h2>Object</h2>
 * <p>
 * An object is the superinterface for all Vortex objects.
 * Objects can be placed in {@link World}s, and have a {@link Location}.
 * </p>
 */
public interface Object extends Unique, Tickable {
    //
    // Physics
    //

    /**
     * Gets the location of this object.
     *
     * @return {@link Location}
     */
    @Nonnull
    Location getLocation();

    /**
     * Gets the vector of this object.
     *
     * @return {@link Vector}
     */
    @Nonnull
    Vector getVector();

    /**
     * Gets the mass of this object.
     *
     * @return {@link Mass}
     */
    @Nonnull
    Mass getMass();

    /**
     * Gets the volume of this object.
     *
     * @return {@link Volume}
     */
    @Nonnull
    Volume getVolume();

    /**
     * Whether this object is subject to gravity and air resistance.
     *
     * @return {@code true} if this actor obeys physics.
     */
    boolean obeysPhysics();

    /**
     * Gets the drag coefficient of this object.
     * This is used to calculate air resistance.
     *
     * @return Drag coefficient
     */
    double getDragCoefficient();

    /**
     * Sets the location of this object.
     *
     * @param location Location
     */
    void setLocation(@Nonnull Location location);

    /**
     * Sets the vector of this object.
     *
     * @param vector Vector
     */
    void setVector(@Nonnull Vector vector);

    /**
     * Sets the mass of this object.
     *
     * @param mass Mass
     */
    void setMass(@Nonnull Mass mass);

    /**
     * Sets the volume of this object.
     *
     * @param volume Volume
     */
    void setVolume(@Nonnull Volume volume);

    /**
     * Sets whether this object is subject to gravity and air resistance.
     *
     * @param obeysPhysics {@code true} to obey physics
     */
    void setObeysPhysics(boolean obeysPhysics);

    /**
     * Sets the drag coefficient of this object.
     *
     * @param coefficient Drag coefficient
     */
    void setDragCoefficient(double coefficient);

}
