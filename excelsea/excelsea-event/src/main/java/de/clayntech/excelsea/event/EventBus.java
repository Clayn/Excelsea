package de.clayntech.excelsea.event;

public interface EventBus extends EventProducer {
    void fireEvent(Event event);
}
