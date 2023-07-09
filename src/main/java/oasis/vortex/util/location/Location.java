package oasis.vortex.util.location;

import oasis.vortex.world.World;

import javax.annotation.Nonnull;

/**
 * <h2>Location</h2>
 * <p>
 * Represents a point in a three-dimensional plane.
 * Since yaw and pitch represent the rotation of an object,
 * their getters are designed to convert them to within the acceptable range.
 * </p>
 *
 * @param world World this location is in
 * @param x     X coordinate of this location (scalar of width)
 * @param y     Y coordinate of this location (scalar of height)
 * @param z     Z coordinate of this location (scalar of depth)
 * @param yaw   Yaw (horizontal scalar)
 * @param pitch Pitch (vertical scalar)
 */
public record Location(
        @Nonnull World world,
        double x,
        double y,
        double z,
        double yaw, // -180 <= yaw <= 180
        double pitch // -180 <= yaw <= 180
) {
    /**
     * Constructor which ignores yaw and pitch.
     *
     * @param world World this location is in
     * @param x     X coordinate of this location
     * @param y     Y coordinate of this location
     * @param z     Z coordinate of this location
     */
    public Location(@Nonnull World world, double x, double y, double z) {
        this(world, x, y, z, 0, 0);
    }

    /**
     * Gets the builder instance for easier construction.
     * To edit this location, use either the modifier methods,
     * or {@link Builder}.
     * Note that world is null by default and requires to be set.
     *
     * @return Blank builder instance.
     */
    @Nonnull
    public static Builder builder() {
        return new Builder();
    }

    // Getters

    /**
     * Converts the yaw to fit within the accepted range.
     *
     * @return Yaw
     */
    @Override
    public double yaw() {
        if (yaw > 180 || yaw < 180) {
            return ((yaw + 180) % 360) - 180;
        }

        return yaw;
    }

    /**
     * Converts the pitch to fit within the accepted range.
     *
     * @return Pitch
     */
    @Override
    public double pitch() {
        if (pitch > 180 || pitch < 180) {
            return ((pitch + 180) % 360) - 180;
        }
        return pitch;
    }

    // Modifiers

    /**
     * Adds delta to X coordinate.
     *
     * @param delta Delta to add
     * @return Resulting location
     */
    @Nonnull
    public Location plusX(double delta) {
        return toBuilder().x(x + delta).build();
    }

    /**
     * Adds delta to Y coordinate.
     *
     * @param delta Delta to add
     * @return Resulting location
     */
    @Nonnull
    public Location plusY(double delta) {
        return toBuilder().y(y + delta).build();
    }

    /**
     * Adds delta to Z coordinate.
     *
     * @param delta Delta to add
     * @return Resulting location
     */
    @Nonnull
    public Location plusZ(double delta) {
        return toBuilder().z(z + delta).build();
    }

    /**
     * Adds delta to yaw.
     *
     * @param delta Delta to add
     * @return Resulting yaw
     */
    @Nonnull
    public Location plusYaw(double delta) {
        return toBuilder().yaw(yaw + delta).build();
    }

    /**
     * Adds delta to pitch.
     *
     * @param delta Delta to add
     * @return Resulting pitch
     */
    @Nonnull
    public Location plusPitch(double delta) {
        return toBuilder().pitch(pitch + delta).build();
    }

    /**
     * Adds another location to this location.
     *
     * @param other Location to add
     * @return Resulting location
     * @throws IllegalArgumentException When the worlds are different
     */
    @Nonnull
    public Location plusLocation(@Nonnull Location other) throws IllegalArgumentException {
        if (!world.equals(other.world)) throw new IllegalArgumentException();

        return toBuilder()
                .x(x + other.x)
                .y(y + other.y)
                .z(z + other.z)
                .yaw(yaw + other.yaw)
                .pitch(pitch + other.pitch)
                .build();
    }

    /**
     * Get a builder pre-populated with the data of this location.
     *
     * @return Builder from location
     */
    public Builder toBuilder() {return new Builder(this);}

    private Location(@Nonnull Builder builder) {
        this(builder.world, builder.x, builder.y, builder.z, builder.yaw, builder.pitch);
    }

    /**
     * <h2>Location.Builder</h2>
     * <p>Since locations are immutable records, a builder is used to edit its properties.</p>
     */
    public static class Builder {
        @SuppressWarnings("ConstantConditions")
        private Builder() {
            this.world = null;
            this.x = 0;
            this.y = 0;
            this.z = 0;
            this.yaw = 0;
            this.pitch = 0;
        }

        private Builder(@Nonnull Location location) {
            this.world = location.world;
            this.x = location.x;
            this.y = location.y;
            this.z = location.z;
            this.yaw = location.yaw;
            this.pitch = location.pitch;
        }

        @Nonnull
        private World world;
        private double x;
        private double y;
        private double z;
        private double yaw;
        private double pitch;

        @Nonnull
        public Builder world(@Nonnull World world) {
            this.world = world;
            return this;
        }

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
        public Builder yaw(double yaw) {
            this.yaw = yaw;
            return this;
        }

        @Nonnull
        public Builder pitch(double pitch) {
            this.pitch = pitch;
            return this;
        }

        /**
         * Finishes the building sequence and builds the {@link Location}.
         *
         * @return new {@link Location}
         * @throws IllegalArgumentException When the required field {@link Builder#world} is null
         */
        @Nonnull
        @SuppressWarnings("ConstantConditions")
        public Location build() throws IllegalArgumentException {
            if (world == null) throw new IllegalArgumentException();
            return new Location(this);
        }
    }
}
