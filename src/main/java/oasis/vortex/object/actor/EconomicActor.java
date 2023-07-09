package oasis.vortex.object.actor;

import oasis.vortex.util.economy.Portfolio;

import javax.annotation.Nonnull;

/**
 * <h2>EconomicActor</h2>
 * <p>An economic actor is capable of holding assets and participating in the economy.</p>
 */
public interface EconomicActor extends Actor {
    /**
     * Gets the asset portfolio of this actor.
     * @return {@link Portfolio}
     */
    @Nonnull
    Portfolio getPortfolio();
}
