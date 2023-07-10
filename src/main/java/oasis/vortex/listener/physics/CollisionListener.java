package oasis.vortex.listener.physics;

import oasis.vortex.event.physics.CollisionEvent;
import oasis.vortex.listener.Listener;
import oasis.vortex.object.Object;

import javax.annotation.Nonnull;

public final class CollisionListener implements Listener<CollisionEvent> {
    @Override
    public void handle(@Nonnull CollisionEvent event) {
        if (event.isCancelled()) return;

        final Object o1 = event.getObject1();
        final Object o2 = event.getObject2();

        final double m1 = o1.getMass().valueKilograms();
        final double m2 = o2.getMass().valueKilograms();

        o1.setVector(o1.getVector().modifyAll(-1 * (m1 / m2)));
        o2.setVector(o2.getVector().modifyAll(-1 * (m2 / m1)));
        System.out.println("Collision occurred");
    }

    @Nonnull
    @Override
    public Class<CollisionEvent> getEventClass() {
        return CollisionEvent.class;
    }
}
