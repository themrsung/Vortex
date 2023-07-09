package oasis.vortex.event.economy;

import oasis.vortex.event.Event;
import oasis.vortex.event.PreemptiveEvent;
import oasis.vortex.object.actor.EconomicActor;
import oasis.vortex.util.economy.AssetStack;
import oasis.vortex.util.economy.Portfolio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * <h2>PaymentEvent</h2>
 * <p>
 *     A payment event denotes the one-way flow of an {@link AssetStack}
 *     from one {@link EconomicActor} to another.
 * </p>
 */
public class PaymentEvent extends PreemptiveEvent.AbstractPreemptiveEvent {
    /**
     * Gets a new builder instance.
     * @return Builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Gets the sender of this payment.
     * @return {@link EconomicActor}
     */
    @Nonnull
    public EconomicActor getSender() {
        return sender;
    }

    /**
     * Gets the portfolio of the sender.
     * @return Sender's {@link Portfolio}
     */
    @Nonnull
    public Portfolio getSenderPortfolio() {
        return sender.getPortfolio();
    }

    /**
     * Gets the recipient of this payment.
     * @return {@link EconomicActor}
     */
    @Nonnull
    public EconomicActor getRecipient() {
        return recipient;
    }

    /**
     * Gets the portfolio of the recipient.
     * @return Recipient's {@link Portfolio}
     */
    @Nonnull
    public Portfolio getRecipientPortfolio() {
        return recipient.getPortfolio();
    }

    /**
     * Gets the payment to send.
     * @return {@link AssetStack}
     */
    @Nonnull
    public AssetStack getPayment() {
        return payment;
    }

    @Nonnull
    private final EconomicActor sender;
    @Nonnull
    private final EconomicActor recipient;
    @Nonnull
    private final AssetStack payment;

    private PaymentEvent(@Nonnull Builder builder) {
        super(builder().successor);

        this.sender = builder.sender;
        this.recipient = builder.recipient;
        this.payment = builder.payment;
    }

    public static class Builder {
        @SuppressWarnings("ConstantConditions")
        private Builder() {
            this.sender = null;
            this.recipient = null;
            this.payment = null;
            this.successor = null;
        }

        @Nonnull
        private EconomicActor sender;
        @Nonnull
        private EconomicActor recipient;
        @Nonnull
        private AssetStack payment;
        @Nullable
        private Event successor;

        @Nonnull
        public Builder sender(@Nonnull EconomicActor sender) {
            this.sender = sender;
            return this;
        }

        @Nonnull
        public Builder recipient(@Nonnull EconomicActor recipient) {
            this.recipient = recipient;
            return this;
        }

        @Nonnull
        public Builder payment(@Nonnull AssetStack payment) {
            this.payment = payment;
            return this;
        }

        @Nonnull
        public Builder successor(@Nonnull Event successor) {
            this.successor = successor;
            return this;
        }

        /**
         * Finalizes building and returns a {@link PaymentEvent} instance.
         * @return {@link PaymentEvent}
         * @throws IllegalArgumentException When at least one of the required fields is null
         */
        @Nonnull
        @SuppressWarnings("ConstantConditions")
        public PaymentEvent build() throws IllegalArgumentException {
            if (sender == null || recipient == null || payment == null) throw new IllegalArgumentException();
            return new PaymentEvent(this);
        }
    }
}
