package de.clayntech.excelsea.event.impl;

import de.clayntech.excelsea.event.Event;
import de.clayntech.excelsea.event.EventListener;
import de.clayntech.excelsea.event.EventProducer;
import de.clayntech.excelsea.event.EventType;

import java.util.*;
import java.util.stream.Collectors;

public class AbstractEventProducer implements EventProducer {
    protected final Map<EventType<?>, List<EventListener<?>>> listeners=new HashMap<>();

    @SuppressWarnings("unchecked")
    protected final <E extends Event> List<EventListener<E>> getListeners(EventType<E> type) {
        if(!listeners.containsKey(type)) {
            return Collections.emptyList();
        }
        return listeners.get(type)
                .stream()
                .map((obj)->(EventListener<E>)obj)
                .collect(Collectors.toList());
    }

    @Override
    public <E extends Event> void addListener(EventType<E> type, EventListener<E> listener) {
        if(!listeners.containsKey(type)) {
            listeners.put(type,new ArrayList<>());
        }
        listeners.get(type).add(listener);
    }

    @Override
    public <E extends Event> void removeListener(EventType<E> type, EventListener<E> listener) {
        if(!listeners.containsKey(type)) {
            return;
        }
        listeners.get(type).remove(listener);
    }
}
