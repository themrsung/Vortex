package oasis.vortex.world;

import oasis.vortex.Vortex;
import oasis.vortex.event.physics.CollisionEvent;
import oasis.vortex.object.Object;
import oasis.vortex.util.collection.list.BetterArrayList;
import oasis.vortex.util.collection.list.BetterList;
import oasis.vortex.util.collection.set.BetterHashSet;
import oasis.vortex.util.collection.set.BetterSet;
import oasis.vortex.util.string.BetterString;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import javax.validation.constraints.Positive;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <h2>RealisticWorld</h2>
 * <p>
 *     A world that behaves like reality.
 *     Collisions between all objects induce {@link CollisionEvent}s.
 * </p>
 * <p>
 *     Gravity and air density is set to default Earth-like values.
 * </p>
 */
public class RealisticWorld implements World {
    /**
     * Creates a new realistic world.
     *
     * @param uniqueId Unique identifier of this world
     * @param name Name of this world
     */
    public RealisticWorld(@Nonnull UUID uniqueId, @Nonnull BetterString name) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.objects = new BetterHashSet<>();
        this.gravity = DEFAULT_GRAVITY;
        this.airDensity = DEFAULT_AIR_DENSITY;
    }

    private final transient Map<Object, BetterList<Object>> overlaps = new HashMap<>();

    /**
     * <h2>Default tick behavior of {@link RealisticWorld}</h2>
     * <p>
     *     Every object is compared with every other for overlapping TriLocations.
     *     If the TriLocations overlap, a {@link CollisionEvent} is called.
     * </p>
     * @param delta Actual delta between the last tick and this one
     */
    @Override
    public void tick(@Nonnull Duration delta) {
        for (Object o1 : getObjects()) {
            final BetterList<Object> overlapping = overlaps.getOrDefault(o1, new BetterArrayList<>());

            for (Object o2 : getObjects().filter(o -> !o.equals(o1))) {
                if (o1.overlaps(o2)) {
                    if (!overlapping.contains(o2)) {
                        Vortex.getEventTask().callEvent(CollisionEvent.builder()
                                .world(this)
                                .object1(o1)
                                .object2(o2)
                                .build());
                        overlapping.add(o2);
                    }
                } else overlapping.remove(o2);
            }

            overlaps.put(o1, overlapping);
        }
    }

    @Nonnull
    private final UUID uniqueId;
    @Nonnull
    private BetterString name;
    @Nonnull
    private final BetterSet<Object> objects;
    private double gravity;
    @Positive
    private double airDensity;

    @Override
    @Nonnull
    public UUID getUniqueId() {
        return uniqueId;
    }

    @Override
    @Nonnull
    public BetterString getName() {
        return name;
    }

    @Override
    public void setName(@Nonnull BetterString name) {
        this.name = name;
    }

    @Nonnull
    @Override
    public BetterSet<Object> getObjects() {
        return new BetterHashSet<>(objects);
    }

    @Override
    public void addObject(@Nonnull Object object) {
        objects.add(object);
    }

    @Override
    public void removeObject(@Nonnull Object object) {
        objects.remove(object);
    }

    @Override
    public double getGravity() {
        return gravity;
    }

    @Positive
    @Override
    public double getAirDensity() {
        return airDensity;
    }

    @Override
    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    @Override
    public void setAirDensity(@Positive double density) {
        this.airDensity = density;
    }
}
