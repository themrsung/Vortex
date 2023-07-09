package oasis.vortex.util.economy;

import oasis.vortex.util.collection.set.BetterHashSet;
import oasis.vortex.util.collection.set.BetterSet;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

/**
 * <h2>Portfolio</h2>
 * <p>
 * A portfolio holds asset stacks.
 * Portfolios ensure that every stackable asset is stacked into one instance.
 * Entries with 0 quantity will be considered meaningless and removed.
 * </p>
 */
public final class Portfolio {
    /**
     * Creates an empty portfolio.
     */
    public Portfolio() {this.stacks = new BetterHashSet<>();}

    /**
     * Creates a new portfolio from given stacks.
     *
     * @param stacks Stacks to contain
     */
    public Portfolio(@Nonnull AssetStack... stacks) {this.stacks = new BetterHashSet<>(stacks);}

    /**
     * Creates a new portfolio from a collection.
     *
     * @param collection Collection to get stacks from
     */
    public Portfolio(@Nonnull Collection<AssetStack> collection) {this.stacks = new BetterHashSet<>(collection);}

    /**
     * Creates a new portfolio from a set.
     *
     * @param stacks Stacks to contain
     */
    public Portfolio(@Nonnull BetterSet<AssetStack> stacks) {this.stacks = stacks;}

    /**
     * Performs a shallow copy of given portfolio.
     *
     * @param other Portfolio to copy
     */
    public Portfolio(@Nonnull Portfolio other) {this.stacks = other.stacks;}

    // Getters

    /**
     * Gets the stacks stored in this portfolio.
     *
     * @return {@link BetterSet} of stacks
     */
    @Nonnull
    public BetterSet<AssetStack> getStacks() {
        return new BetterHashSet<>(stacks);
    }

    /**
     * Gets the stack of given asset stored in this portfolio.
     *
     * @param asset Asset to query
     * @return {@link AssetStack} if found, {@code null} if not found
     */
    @Nullable
    public AssetStack getStack(@Nonnull Asset asset) {
        for (AssetStack stack : getStacks()) if (stack.getAsset().isStackableWith(asset)) return stack;
        return null;
    }

    /**
     * Adds a stack to this portfolio.
     * Stackable stacks will be combined.
     *
     * @param stack Stack to add
     */
    public void addStack(@Nonnull AssetStack stack) {
        final AssetStack existing = getStack(stack.getAsset());
        if (existing != null) {
            existing.addQuantity(stack.getQuantity());
            stacks.removeIf(s -> s.getQuantity() == 0);
            return;
        }

        stacks.add(new AssetStack(stack));
    }

    /**
     * Removes a stack from this portfolio.
     * This will add a negated stack if this portfolio does not contain the given asset.
     *
     * @param stack Stack to remove
     */
    public void removeStack(@Nonnull AssetStack stack) {
        final AssetStack existing = getStack(stack.getAsset());
        if (existing != null) {
            existing.subtractQuantity(stack.getQuantity());
            stacks.removeIf(s -> s.getQuantity() == 0);
            return;
        }

        // Negates the stack and adds it to stacks
        final AssetStack copy = new AssetStack(stack);
        copy.multiplyQuantity(-1);

        stacks.add(copy);
    }

    /**
     * Gets the quantity of given asset in this portfolio.
     * This will return 0 if this portfolio does not have a corresponding entry.
     *
     * @param asset Asset to query
     * @return {@code quantity} of the asset stack if found, {@code 0} if not found
     */
    public double getQuantityOf(@Nonnull Asset asset) {
        final AssetStack stack = getStack(asset);
        return stack != null ? stack.getQuantity() : 0;
    }

    /**
     * Checks if this portfolio contains at least the specified quantity of given asset.
     * Note that when a zero or negative quantity is provided and this portfolio does not
     * have an existing negative entry of the matching asset, this will return {@code true}.
     *
     * @param stack Stack to query
     * @return Whether this asset contains at least the given quantity.
     */
    public boolean contains(@Nonnull AssetStack stack) {
        return getQuantityOf(stack.getAsset()) >= stack.getQuantity();
    }

    @Nonnull
    private final BetterSet<AssetStack> stacks;
}
