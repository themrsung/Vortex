/**
 * <h1>Events</h1>
 * <p>
 * Events can be used to either perform actions or notify other classes of an executed action.
 * Preemptive events are called before an action in order execute it,
 * while Permissive events are called after an action to notify other classes of the action.
 * </p>
 *
 *
 * <br>
 * <br>
 *
 * <h3>Building event chains</h3>
 * <p>
 * Preemptive events can have one successor event. The successor event can be set to another preemptive event
 * to create an event chain, or a permissive event to end the chain.
 * A chain can also be ended with a preemptive event.
 * </p>
 * <p>
 * In order to build an event chain, events must be constructed in reverse order because events are immutable.
 * For example, if you want to build an event chain A -> B -> C -> D, D needs to be constructed before C,
 * C before B, and so on.
 * If this is not done in reverse order, the successor event cannot be set.
 * </p>
 * <p>
 * All events with a length constructor use builders instead of constructors,
 * so creating events should be relatively easy.
 * </p>
 */
package oasis.vortex.event;