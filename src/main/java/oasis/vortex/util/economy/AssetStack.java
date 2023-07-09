package oasis.vortex.util.economy;

import javax.annotation.Nonnull;

/**
 * <h2>AssetStack</h2>
 * <p>Represents a stack of assets.</p>
 */
public final class AssetStack {
    /**
     * Creates a new asset stack.
     * @param asset Asset to store in this stack
     * @param quantity Quantity of asset
     */
    public AssetStack(@Nonnull Asset asset, double quantity) {
        this.asset = asset;
        this.quantity = quantity;
    }

    /**
     * Gets the asset stored in this stack.
     * @return {@link Asset}
     */
    @Nonnull
    public Asset getAsset() {
        return asset;
    }

    /**
     * Gets the amount of assets stored in this stack.
     * @return Quantity
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of assets stored in this stack.
     * @param quantity Quantity
     */
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /**
     * Adds delta to this stack.
     * @param delta Delta
     */
    public void addQuantity(double delta) {
        this.quantity += delta;
    }

    /**
     * Subtracts delta from this stack.
     * @param delta Delta
     */
    public void subtractQuantity(double delta) {
        this.quantity -= delta;
    }

    /**
     * Multiplies this stack by given modifier.
     * @param modifier Modifier
     */
    public void multiplyQuantity(double modifier) {
        this.quantity *= modifier;
    }

    /**
     * Divides this stack by given denominator.
     * @param denominator Denominator
     * @throws ArithmeticException When denominator is zero
     */
    public void divideQuantity(double denominator) throws ArithmeticException {
        this.quantity /= denominator;
    }

    @Nonnull
    private final Asset asset;
    private double quantity;
}
