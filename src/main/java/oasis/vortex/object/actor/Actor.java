package oasis.vortex.object.actor;

import oasis.vortex.object.Object;
import oasis.vortex.util.string.BetterString;

import javax.annotation.Nonnull;

/**
 * <h2>Actor</h2>
 * <p>An actor is a physical object which exists inside a world.</p>
 */
public interface Actor extends Object {
    /**
     * Gets the name of this actor.
     *
     * @return Name
     */
    @Nonnull
    BetterString getName();

    /**
     * Sets the name of this actor.
     * Name cannot be null. To not give a name, pass {@code new Text()} or {@code BetterString.blank()} as the parameter.
     *
     * @param name Name
     */
    void setName(@Nonnull BetterString name);
}
