package oasis.vortex.event;

/**
 * <h2>PermissiveEvent</h2>
 * <p>
 *     A permissive event can only be placed at the end of an event chain,
 *     and cannot be cancelled.
 * </p>
 * @see Event
 */
public interface PermissiveEvent extends Event {
    abstract class AbstractPermissiveEvent extends AbstractEvent implements PermissiveEvent {
        public AbstractPermissiveEvent() {}
    }
}
