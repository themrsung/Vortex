package oasis.vortex.util.location;

/**
 * <h2>Vector</h2>
 * <p>
 * Represents a direction in three-dimensional context.
 * This is used to represent acceleration.
 * </p>
 * <p>
 * All parameters are values per tick.
 * </p>
 *
 * @param x The velocity of the X coordinate per tick
 * @param y The velocity of the Y coordinate per tick
 * @param z The velocity of the Z coordinate per tick
 */
public record Vector(
        double x,
        double y,
        double z
) {

}
