package oasis.vortex.util.movement;

import javax.annotation.Nonnull;

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
    /**
     * Creates a new vector with default values {@code 0, 0, 0}.
     */
    public Vector() {
        this(0, 0, 0);
    }

    /**
     * Gets a new empty builder instance.
     * @return Builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Sets the X value of this vector.
     *
     * @param x X
     * @return Resulting vector
     */
    @Nonnull
    public Vector setX(double x) {
        return toBuilder().x(x).build();
    }

    /**
     * Sets the Y value of this vector.
     *
     * @param y Y
     * @return Resulting vector
     */
    @Nonnull
    public Vector setY(double y) {
        return toBuilder().y(y).build();
    }

    /**
     * Sets the Z value of this vector.
     *
     * @param z Z
     * @return Resulting vector
     */
    @Nonnull
    public Vector setZ(double z) {
        return toBuilder().z(z).build();
    }

    /**
     * Adds delta to the X value of this vector.
     *
     * @param delta Delta
     * @return Resulting vector
     */
    @Nonnull
    public Vector plusX(double delta) {
        return toBuilder().x(x + delta).build();
    }

    /**
     * Adds delta to the Y value of this vector.
     *
     * @param delta Delta
     * @return Resulting vector
     */
    @Nonnull
    public Vector plusY(double delta) {
        return toBuilder().y(y + delta).build();
    }

    /**
     * Adds delta to the z value of this vector.
     *
     * @param delta Delta
     * @return Resulting vector
     */
    @Nonnull
    public Vector plusZ(double delta) {
        return toBuilder().z(z + delta).build();
    }

    /**
     * Modifies the X value of this vector.
     *
     * @param modifier Modifier
     * @return Resulting vector
     */
    @Nonnull
    public Vector modifyX(double modifier) {
        return toBuilder().x(x * modifier).build();
    }

    /**
     * Modifies the Y value of this vector.
     *
     * @param modifier Modifier
     * @return Resulting vector
     */
    @Nonnull
    public Vector modifyY(double modifier) {
        return toBuilder().y(y * modifier).build();
    }

    /**
     * Modifies the Z value of this vector.
     *
     * @param modifier Modifier
     * @return Resulting vector
     */
    @Nonnull
    public Vector modifyZ(double modifier) {
        return toBuilder().z(z * modifier).build();
    }

    /**
     * Converts this vector to builder for modification.
     *
     * @return Builder
     */
    @Nonnull
    public Builder toBuilder() {
        return new Builder(this);
    }

    private Vector(@Nonnull Builder builder) {
        this(builder.x, builder.y, builder.z);
    }

    public static final class Builder {
        private Builder() {
            this.x = 0;
            this.y = 0;
            this.z = 0;
        }
        private Builder(@Nonnull Vector vector) {
            this.x = vector.x;
            this.y = vector.y;
            this.z = vector.z;
        }

        private double x;
        private double y;
        private double z;

        @Nonnull
        public Builder x(double x) {
            this.x = x;
            return this;
        }

        @Nonnull
        public Builder y(double y) {
            this.y = y;
            return this;
        }

        @Nonnull
        public Builder z(double z) {
            this.z = z;
            return this;
        }

        @Nonnull
        public Builder xyz(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
            return this;
        }

        @Nonnull
        public Vector build() {
            return new Vector(this);
        }
    }
}
