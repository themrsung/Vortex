package oasis.vortex.object;

import oasis.vortex.util.physics.Location;
import oasis.vortex.util.physics.Mass;
import oasis.vortex.util.physics.Vector;
import oasis.vortex.util.physics.Volume;
import org.joda.time.Duration;

import javax.annotation.Nonnull;
import javax.validation.constraints.Positive;
import java.util.UUID;

/**
 * <h2>AbstractObject</h2>
 * <p>
 *     This is the default superimplementation of {@link Object}.
 *     Parameters and methods present in all objects are defined and handled.
 * </p>
 * <p>
 *     This class is abstract, and is not intended to be instantiated directly.
 * </p>
 */
public abstract class AbstractObject implements Object {
    /**
     * Default minimal constructor.
     * Initial values for undefined fields are as follows.
     * <ul>
     *     <li>vector: {@code new Vector()}</li>
     *     <li>fluid: {@code false}</li>
     *     <li>obeysPhysics: {@code true}</li>
     * </ul>
     *
     * @param uniqueId Unique ID
     * @param location Location
     * @param mass Mass
     * @param volume Volume
     * @param dragCoefficient Drag coefficient
     */
    protected AbstractObject(
            @Nonnull UUID uniqueId,
            @Nonnull Location location,
            @Nonnull Mass mass,
            @Nonnull Volume volume,
            double dragCoefficient
    ) {
        this.uniqueId = uniqueId;
        this.location = location;
        this.vector = new Vector();
        this.mass = mass;
        this.volume = volume;
        this.fluid = false;
        this.obeysPhysics = true;
        this.dragCoefficient = dragCoefficient;
    }

    /**
     * Default all-args constructor.
     *
     * @param uniqueId Unique ID
     * @param location Location
     * @param vector Vector
     * @param mass Mass
     * @param volume Volume
     * @param fluid Fluid
     * @param obeysPhysics Whether this object obeys physics
     * @param dragCoefficient Drag coefficient
     */
    protected AbstractObject(
            @Nonnull UUID uniqueId,
            @Nonnull Location location,
            @Nonnull Vector vector,
            @Nonnull Mass mass,
            @Nonnull Volume volume,
            boolean fluid,
            boolean obeysPhysics,
            double dragCoefficient
    ) {
        this.uniqueId = uniqueId;
        this.location = location;
        this.vector = vector;
        this.mass = mass;
        this.volume = volume;
        this.fluid = fluid;
        this.obeysPhysics = obeysPhysics;
        this.dragCoefficient = dragCoefficient;
    }

    /**
     * Default copy constructor.
     * @param other Other object
     */
    protected AbstractObject(@Nonnull AbstractObject other) {
        this.uniqueId = other.uniqueId;
        this.location = other.location;
        this.vector = other.vector;
        this.mass = other.mass;
        this.volume = other.volume;
        this.fluid = other.fluid;
        this.obeysPhysics = other.obeysPhysics;
        this.dragCoefficient = other.dragCoefficient;
    }

    //
    // Variables
    //
    @Nonnull
    private final UUID uniqueId;
    @Nonnull
    private Location location;
    @Nonnull
    private Vector vector;
    @Nonnull
    private Mass mass;
    @Nonnull
    private Volume volume;
    private boolean fluid;
    private boolean obeysPhysics;
    @Positive
    private double dragCoefficient;

    //
    // Tick
    //

    /**
     * Does nothing by default. Override to add behavior.
     * @param delta Actual delta between the last tick and this one
     */
    @Override
    public void tick(@Nonnull Duration delta) {}


    //
    // Methods
    //

    @Override
    @Nonnull
    public UUID getUniqueId() {
        return uniqueId;
    }

    @Override
    @Nonnull
    public Location getLocation() {
        return location;
    }

    @Override
    @Nonnull
    public Vector getVector() {
        return vector;
    }

    @Override
    @Nonnull
    public Mass getMass() {
        return mass;
    }

    @Override
    @Nonnull
    public Volume getVolume() {
        return volume;
    }

    @Override
    public boolean isFluid() {
        return fluid;
    }

    @Override
    public boolean obeysPhysics() {
        return obeysPhysics;
    }

    @Positive
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

    @Override
    public void setFluid(boolean fluid) {
        this.fluid = fluid;
    }

    @Override
    public void setObeysPhysics(boolean obeysPhysics) {
        this.obeysPhysics = obeysPhysics;
    }

    @Override
    public void setDragCoefficient(@Positive double dragCoefficient) {
        this.dragCoefficient = dragCoefficient;
    }
}
