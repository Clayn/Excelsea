package de.clayntech.excelsea.event;

import de.clayntech.excelsea.event.impl.EventBusImpl;

import java.util.function.Consumer;

public interface EventBus extends EventProducer {
    void fireEvent(Event event);

    /**
     * Creates a new event bus that will handle events in a separate thread but will dispatch them to the listeners
     * using the given executor. If the executor is {@code null} the event thread will be used for execution.
     * @param executor the executor that will handle the dispatching of the events
     * @return a new event bus
     */
    static EventBus createEventBus(Consumer<Runnable> executor) {
        return new EventBusImpl(executor);
    }

}
