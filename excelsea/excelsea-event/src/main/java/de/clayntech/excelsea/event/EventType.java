package de.clayntech.excelsea.event;

import java.util.Objects;

public class EventType<E extends Event> {

    public static final EventType<?> ROOT=new EventType<>(null,"EVENT_ROOT");

    private final EventType<?> parent;
    private final String typeName;

    public EventType(EventType<?> parent, String typeName) {
        this.parent = parent;
        this.typeName = typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventType)) return false;
        EventType<?> eventType = (EventType<?>) o;
        return Objects.equals(parent, eventType.parent) &&
                Objects.equals(typeName, eventType.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, typeName);
    }
}
