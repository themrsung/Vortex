package oasis.vortex.event.physics;

import oasis.vortex.event.Event;
import oasis.vortex.event.PreemptiveEvent;
import oasis.vortex.object.Object;
import oasis.vortex.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * <h2>CollisionEvent</h2>
 * <p>
 * Called when two {@link Object}s collide with each other.
 * This event is not guaranteed to be called only once.
 * This is called every tick while the two objects' TriLocation overlap.
 * </p>
 * <p>
 * Cancelling this event does not cancel the movement of the objects.
 * It only cancels the handling of this event.
 * </p>
 *
 * @see Object#overlaps(Object)
 * @see oasis.vortex.util.physics.TriLocation
 */
public class CollisionEvent extends PreemptiveEvent.AbstractPreemptiveEvent {
    /**
     * Gets a new builder instance.
     *
     * @return Builder
     */
    @Nonnull
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Gets the first object involved in this event.
     *
     * @return Object 1
     */
    @Nonnull
    public Object getObject1() {
        return object1;
    }

    /**
     * Gets the second object involved in this event.
     *
     * @return Object 2
     */
    @Nonnull
    public Object getObject2() {
        return object2;
    }

    /**
     * Gets the world this collision occurred in.
     *
     * @return World
     */
    @Nonnull
    public World getWorld() {
        return world;
    }

    /**
     * Checks if either of the two objects are fluids.
     *
     * @return {@code true} if either of the two objects' {@link Object#isFluid()} returns true
     */
    public boolean isFluid() {
        return object1.isFluid() || object2.isFluid();
    }

    @Nonnull
    private final Object object1;

    @Nonnull
    private final Object object2;

    @Nonnull
    private final World world;

    private CollisionEvent(@Nonnull Builder builder) {
        super(builder.successor);
        this.object1 = builder.object1;
        this.object2 = builder.object2;
        this.world = builder.world;
    }

    public static final class Builder {
        @SuppressWarnings("ConstantConditions")
        private Builder() {
            this.object1 = null;
            this.object2 = null;
            this.world = null;
            this.successor = null;
        }

        @Nonnull
        private Object object1;
        @Nonnull
        private Object object2;
        @Nonnull
        private World world;
        @Nullable
        private Event successor;

        @Nonnull
        public Builder object1(@Nonnull Object o1) {
            this.object1 = o1;
            return this;
        }

        @Nonnull
        public Builder object2(@Nonnull Object o2) {
            this.object2 = o2;
            return this;
        }

        @Nonnull
        public Builder world(@Nonnull World world) {
            this.world = world;
            return this;
        }

        @Nonnull
        public Builder successor(@Nonnull Event successor) {
            this.successor = successor;
            return this;
        }

        /**
         * Builds a CollisionEvent.
         *
         * @return Built event
         * @throws IllegalArgumentException When at least one of the required parameters are null
         */
        @Nonnull
        @SuppressWarnings("ConstantConditions")
        public CollisionEvent build() throws IllegalArgumentException {
            if (object1 == null || object2 == null || world == null) throw new IllegalArgumentException();
            return new CollisionEvent(this);
        }
    }
}
