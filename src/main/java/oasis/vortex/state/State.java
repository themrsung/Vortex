package oasis.vortex.state;

import oasis.vortex.object.Object;
import oasis.vortex.tickable.Tickable;
import oasis.vortex.util.collection.list.BetterList;
import oasis.vortex.util.collection.set.BetterSet;
import oasis.vortex.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

/**
 * <h2>State</h2>
 * <p>A state represents the state of a Vortex game.</p>
 */
public interface State extends Tickable {
    //
    // Worlds
    //

    /**
     * Gets a list of all worlds in this state.
     *
     * @return List of {@link World}s.
     */
    @Nonnull
    BetterList<World> getWorlds();

    /**
     * Adds a world to this state.
     *
     * @param world World to add
     */
    void addWorld(@Nonnull World world);

    /**
     * Removes a world from this state.
     *
     * @param world World to remove
     */
    void removeWorld(@Nonnull World world);

    //
    // Objects
    //

    /**
     * Gets a superset of objects from all worlds.
     *
     * @return Set of {@link Object}s.
     */
    @Nonnull
    BetterSet<Object> getObjects();

    /**
     * Searches for an object within this state.
     *
     * @param type     Type of object to search
     * @param uniqueId Unique identifier of the object
     * @param <O>      Subtype of {@link Object}
     * @return {@link ObjectQueryResult}
     */
    @Nonnull
    <O extends Object> ObjectQueryResult<O> queryObject(@Nonnull Class<O> type, @Nonnull UUID uniqueId);

    //
    // Util
    //

    /**
     * The result of {@link State#queryObject(Class, UUID)}.
     *
     * @param object Object matching given UUID (can be null)
     * @param world  World the object was found in (can be null)
     * @param <O>    Type of object queried
     */
    record ObjectQueryResult<O extends Object>(
            @Nullable O object,
            @Nullable World world
    ) {
        /**
         * Checks if an object was found.
         *
         * @return {@code true} if both object and world are not null
         */
        public boolean found() {return object != null && world != null;}

        /**
         * Getter for object which guarantees non-null.
         * Check if object was found with {@link ObjectQueryResult#found()} first.
         *
         * @return {@link O}
         * @throws NullPointerException When the pointer is null
         */
        @Nonnull
        public O objectNonNull() throws NullPointerException {
            if (object == null) throw new NullPointerException();
            return object;
        }

        /**
         * Getter for world which guarantees non-null.
         * Check if object was found with {@link ObjectQueryResult#found()} first.
         *
         * @return {@link World}
         * @throws NullPointerException When the pointer is null
         */
        @Nonnull
        public World worldNonNull() throws NullPointerException {
            if (world == null) throw new NullPointerException();
            return world;
        }
    }
}