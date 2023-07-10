package oasis.vortex.world;

import oasis.vortex.object.Object;
import oasis.vortex.util.collection.set.BetterHashSet;
import oasis.vortex.util.collection.set.BetterSet;
import oasis.vortex.util.string.BetterString;
import oasis.vortex.util.string.Text;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import javax.validation.constraints.Positive;
import java.util.UUID;

/**
 * <h2>DummyWorld</h2>
 * <p>A world with no special traits. Used for debugging.</p>
 */
public class DummyWorld implements World {
    public DummyWorld() {
        this.uniqueId = UUID.randomUUID();
        this.name = new Text("World 1!!!");
        this.objects = new BetterHashSet<>();
        this.gravity = DEFAULT_GRAVITY;
        this.airDensity = DEFAULT_AIR_DENSITY;
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
    public void tick(@Nonnull Duration delta) {
        // ground level is 0

        objects.forEach(o -> {
            if (o.getLocation().y() <= 0) {
                o.setLocation(o.getLocation().setY(0));
                o.setVector(o.getVector().modifyY(0));
            }
        });
    }

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

    @Override
    @Nonnull
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

    @Override
    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    @Override
    @Positive
    public double getAirDensity() {
        return airDensity;
    }

    @Override
    public void setAirDensity(@Positive double airDensity) {
        this.airDensity = airDensity;
    }
}
