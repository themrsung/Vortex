package oasis.vortex.listener.dummy;

import oasis.vortex.event.dummy.DummyEvent;
import oasis.vortex.listener.Listener;
import org.joda.time.DateTime;

import javax.annotation.Nonnull;

public final class DummyListener implements Listener<DummyEvent> {
    @Override
    public void handle(@Nonnull DummyEvent event) {
        System.out.println("Dummy event executed at " + DateTime.now().getMillis());
    }

    @Nonnull
    @Override
    public Class<DummyEvent> getEventClass() {
        return DummyEvent.class;
    }
}
