package oasis.vortex.object;

import oasis.vortex.util.physics.Location;
import oasis.vortex.util.physics.Mass;
import oasis.vortex.util.physics.Vector;
import oasis.vortex.util.physics.Volume;
import oasis.vortex.world.World;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * <h2>DummyObject</h2>
 * <p>An object that does nothing. Used for debugging.</p>
 */
public class DummyObject implements Object {
    public DummyObject(@Nonnull World world) {
        this.uniqueId = UUID.randomUUID();
        this.location = Location.builder().world(world).build();
        this.vector = new Vector();
        this.mass = new Mass(100, Mass.Unit.KILOGRAM);
        this.volume = new Volume();
        this.obeysPhysics = true;
        this.dragCoefficient = 1;
    }

    private final UUID uniqueId;
    private Location location;
    private Vector vector;
    private Mass mass;
    private Volume volume;
    private boolean obeysPhysics;
    private double dragCoefficient;

    @Override
    public void tick(@Nonnull Duration delta) {

    }

    @Nonnull
    @Override
    public UUID getUniqueId() {
        return uniqueId;
    }

    @Nonnull
    @Override
    public Location getLocation() {
        return location;
    }

    @Nonnull
    @Override
    public Vector getVector() {
        return vector;
    }

    @Override
    public double getVelocity() {
        return vector.getVelocity();
    }

    @Nonnull
    @Override
    public Mass getMass() {
        return mass;
    }

    @Nonnull
    @Override
    public Volume getVolume() {
        return volume;
    }

    @Override
    public boolean obeysPhysics() {
        return obeysPhysics;
    }

    @Override
    public double getDragCoefficient() {
        return dragCoefficient;
    }

    @Override
    public void setLocation(@Nonnull Location location) {
        this.location = location;
    }

    @Override
    public void setVector(@Nonnull Vector vector) {
        this.vector = vector;
    }

    @Override
    public void setMass(@Nonnull Mass mass) {
        this.mass = mass;
    }

    @Override
    public void setVolume(@Nonnull Volume volume) {
        this.volume = volume;
    }

    public void setObeysPhysics(boolean obeysPhysics) {
        this.obeysPhysics = obeysPhysics;
    }

    public void setDragCoefficient(double dragCoefficient) {
        this.dragCoefficient = dragCoefficient;
    }
}
