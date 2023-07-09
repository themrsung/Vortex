package oasis.vortex.listener.economy;

import oasis.vortex.event.economy.PaymentEvent;
import oasis.vortex.listener.Listener;
import oasis.vortex.object.actor.EconomicActor;
import oasis.vortex.util.economy.AssetStack;

import javax.annotation.Nonnull;

/**
 * <h2>PaymentListener</h2>
 * <p>Handles the payment between {@link EconomicActor}s.</p>
 */
public final class PaymentListener implements Listener<PaymentEvent> {
    @Override
    public void handle(@Nonnull PaymentEvent event) {
        if (event.isCancelled()) return;

        final EconomicActor sender = event.getSender();
        final EconomicActor recipient = event.getRecipient();
        final AssetStack payment = event.getPayment();

        if (!sender.getPortfolio().contains(payment)) return;

        sender.getPortfolio().removeStack(payment);
        recipient.getPortfolio().addStack(payment);
    }

    @Nonnull
    @Override
    public Class<PaymentEvent> getEventClass() {
        return PaymentEvent.class;
    }
}
