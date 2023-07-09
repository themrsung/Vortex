package oasis.vortex.object;

import java.util.UUID;

/**
 * <h2>Object</h2>
 * <p>An object is the superinterface for all Vortex objects.</p>
 */
public interface Object {
    /**
     * Gets the unique identifier of this object.
     * @return {@link UUID}
     */
    UUID getUniqueId();
}
