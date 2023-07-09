package oasis.vortex.state;

import oasis.vortex.object.Object;
import oasis.vortex.util.collection.list.BetterArrayList;
import oasis.vortex.util.collection.list.BetterList;
import oasis.vortex.util.collection.set.BetterHashSet;
import oasis.vortex.util.collection.set.BetterSet;
import oasis.vortex.world.World;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * <h2>VortexState</h2>
 * <p>The default implementation of {@link State}.</p>
 */
public final class VortexState implements State {
    /**
     * Creates an empty state.
     */
    public VortexState() {
        this.worlds = new BetterArrayList<>();
    }


    @Nonnull
    private final BetterList<World> worlds;

    @Override
    @Nonnull
    public BetterList<World> getWorlds() {
        return new BetterArrayList<>(worlds);
    }

    @Nonnull
    @Override
    public BetterSet<Object> getObjects() {
        BetterSet<Object> objects = new BetterHashSet<>();
        worlds.forEach(w -> objects.addAll(w.getObjects()));
        return objects;
    }

    @Nonnull
    @Override
    public <O extends Object> ObjectQueryResult<O> queryObject(@Nonnull Class<O> type, @Nonnull UUID uniqueId) {
        for (World world : worlds) {
            BetterList<O> results = new BetterArrayList<>(world.getObjects().filter(type, o -> o.getUniqueId().equals(uniqueId)));
            if (results.size() != 0) {
                return new ObjectQueryResult<>(results.get(0), world);
            }
        }

        return new ObjectQueryResult<>(null, null);
    }

    @Override
    public void addWorld(@Nonnull World world) {
        worlds.add(world);
    }

    @Override
    public void removeWorld(@Nonnull World world) {
        worlds.remove(world);
    }

    @Override
    public void tick(@Nonnull Duration delta) {
        worlds.forEach(w -> w.tick(delta));
    }
}
