package de.clayntech.excelsea.event;

import de.clayntech.excelsea.common.Stub;
import de.clayntech.excelsea.event.impl.EventBusImpl;

public final class EventBusStub extends Stub<EventBus> implements EventBus {

    public EventBusStub() {
        this(new EventBusImpl());
    }

    public EventBusStub(EventBus delegate) {
        super(delegate);
    }

    @Override
    public void fireEvent(Event event) {
        delegate.fireEvent(event);
    }

    @Override
    public <E extends Event> void addListener(EventType<E> type, EventListener<E> listener) {
        delegate.addListener(type, listener);
    }

    @Override
    public <E extends Event> void removeListener(EventType<E> type, EventListener<E> listener) {
        delegate.removeListener(type, listener);
    }
}
