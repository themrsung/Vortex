package oasis.vortex.util.economy;

import javax.annotation.Nonnull;

/**
 * <h2>Asset</h2>
 * <p>
 *     An asset represents an ownership or a right.
 *     Assets are immutable, and do not have a quantity.
 *     Quantities are represented in {@link AssetStack}s.
 * </p>
 */
public interface Asset {
    /**
     * Gets the unique symbol of this asset.
     * @return Unique symbol
     */
    @Nonnull
    String getSymbol();
}
