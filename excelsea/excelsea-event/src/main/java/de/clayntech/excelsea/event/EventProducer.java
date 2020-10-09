package de.clayntech.excelsea.event;

/**
 * Implementations of this class can have {@link EventListener event listeners} registered.
 * Implementing class should provide methods that restrict the listener types for better usage.
 */
public interface EventProducer {
    <E extends Event> void addListener(EventType<E> type,EventListener<E> listener);

    <E extends Event> void removeListener(EventType<E> type,EventListener<E> listener);
}
