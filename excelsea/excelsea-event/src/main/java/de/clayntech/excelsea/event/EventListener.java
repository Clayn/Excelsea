package de.clayntech.excelsea.event;

public interface EventListener<E extends Event> {
    void handle(E event);
}
