package oasis.vortex.object;

import oasis.vortex.util.physics.Location;
import oasis.vortex.util.physics.Mass;
import oasis.vortex.util.physics.Vector;
import oasis.vortex.util.physics.Volume;
import oasis.vortex.world.World;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * <h2>ImmovableObject</h2>
 * <p>
 *     An immovable object's mass is set to {@link Double#MAX_VALUE} grams,
 *     and cannot be moved after creation.
 * </p>
 * <p>
 *     The following methods will do nothing.
 *     <ul>
 *         <li>{@link ImmovableObject#setMass(Mass)}</li>
 *         <li>{@link ImmovableObject#setLocation(Location)}</li>
 *         <li>{@link ImmovableObject#setVector(Vector)}</li>
 *         <li>{@link ImmovableObject#setDragCoefficient(double)}</li>
 *     </ul>
 * </p>
 */
public class ImmovableObject extends AbstractObject {
    /**
     * Gets a new builder instance.
     * @return Builder
     */
    public static Builder builder() { return new Builder(); }

    /**
     * Creates a new ground object.
     *
     * @param world World of the object
     * @param groundLevel Ground level (Y coordinate)
     * @return Ground object
     */
    @Nonnull
    public static ImmovableObject ground(@Nonnull World world, double groundLevel) {
        final double maxNegativeToGround = groundLevel - (-Double.MAX_VALUE);
        return builder()
                .location(Location.builder().world(world).y(groundLevel - maxNegativeToGround / 2).build())
                .volume(Volume.builder().xyz(
                        Double.MAX_VALUE,
                        maxNegativeToGround,
                        Double.MAX_VALUE
                ).build())
                .fluid(false)
                .build();
    }

    /**
     * Performs a shallow copy.
     * @param other Object to copy
     */
    public ImmovableObject(@Nonnull ImmovableObject other) { super(other); }

    private ImmovableObject(@Nonnull Builder builder) {
        super(
                builder.uniqueId,
                builder.location,
                new Vector(),
                new Mass(Double.MAX_VALUE, Mass.Unit.GRAM),
                builder.volume,
                builder.fluid,
                false,
                Double.MIN_VALUE
        );
    }

    public static class Builder {
        @SuppressWarnings("ConstantConditions")
        private Builder() {
            this.uniqueId = UUID.randomUUID();
            this.location = null;
            this.volume = null;
            this.fluid = false;
        }

        @Nonnull
        private UUID uniqueId;
        @Nonnull
        private Location location;
        @Nonnull
        private Volume volume;
        private boolean fluid;

        @Nonnull
        public Builder uniqueId(@Nonnull UUID uniqueId) {
            this.uniqueId = uniqueId;
            return this;
        }

        @Nonnull
        public Builder location(@Nonnull Location location) {
            this.location = location;
            return this;
        }

        @Nonnull
        public Builder volume(@Nonnull Volume volume) {
            this.volume = volume;
            return this;
        }

        @Nonnull
        public Builder fluid(boolean fluid) {
            this.fluid = fluid;
            return this;
        }

        @Nonnull
        @SuppressWarnings("ConstantConditions")
        public ImmovableObject build() throws IllegalArgumentException {
            if (uniqueId == null || location == null || volume == null) throw new IllegalArgumentException();
            return new ImmovableObject(this);
        }
    }

    //
    // Constants
    //

    /**
     * Returns {@link Double#MAX_VALUE} grams.
     * @return {@link Double#MAX_VALUE} grams
     */
    @Nonnull
    @Override
    public Mass getMass() {
        return new Mass(Double.MAX_VALUE, Mass.Unit.GRAM); // Any unit other than grams will result in overflows.
    }

    /**
     * Returns new vector.
     * @return {@code new Vector()}
     */
    @Nonnull
    @Override
    public Vector getVector() {
        return new Vector();
    }

    /**
     * Always returns {@link Double#MIN_VALUE}.
     * @return {@link Double#MIN_VALUE}
     */
    @Override
    public double getDragCoefficient() { return Double.MIN_VALUE; }

    /**
     * Always returns false; Immovable objects do not obey physics.
     * @return {@code false}
     */
    @Override
    public boolean obeysPhysics() { return false; }

    /**
     * Does nothing; Immovable objects have a constant mass.
     * @param mass Mass
     */
    @Override
    public void setMass(@Nonnull Mass mass) {}

    /**
     * Does nothing; Immovable objects cannot be moved.
     * @param location Location
     */
    @Override
    public void setLocation(@Nonnull Location location) {}

    /**
     * Does nothing. Acceleration of immovable objects is meaningless.
     * @param vector Vector
     */
    @Override
    public void setVector(@Nonnull Vector vector) {}

    /**
     * Does nothing. Drag coefficient of an immovable object is constant.
     * @param dragCoefficient Drag coefficient
     */
    @Override
    public void setDragCoefficient(double dragCoefficient) {}
}
