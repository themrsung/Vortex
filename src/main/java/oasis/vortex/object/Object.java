package oasis.vortex.object;

import oasis.vortex.task.tick.Tickable;
import oasis.vortex.util.location.Location;
import oasis.vortex.util.location.Vector;
import oasis.vortex.util.meta.Unique;
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
    // Location & Vector
    //

    /**
     * Gets the location of this object.
     *
     * @return Location
     */
    @Nonnull
    Location getLocation();

    /**
     * Gets the vector of this object.
     *
     * @return Vector
     */
    @Nonnull
    Vector getVector();

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

}
