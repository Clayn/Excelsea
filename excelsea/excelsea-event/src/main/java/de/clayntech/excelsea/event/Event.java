package de.clayntech.excelsea.event;

public abstract class Event {
    private final long timestamp;
    private boolean consumed=false;
    private final Object source;
    private final EventType<?> type;

    public Event(Object source,EventType<?> type) {
        this.source=source;
        this.type=type;
        this.timestamp=System.currentTimeMillis();
    }

    public EventType<?> getType() {
        return type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Object getSource() {
        return source;
    }

    public boolean isConsumed() {
        return consumed;
    }

    public void consume() {
        consumed=true;
    }
}
