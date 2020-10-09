package de.clayntech.excelsea.event.impl;

import de.clayntech.excelsea.event.Event;
import de.clayntech.excelsea.event.EventBus;
import de.clayntech.excelsea.event.EventListener;
import de.clayntech.excelsea.event.EventType;

import java.util.ArrayDeque;
import java.util.Queue;

public class EventBusImpl extends AbstractEventProducer implements EventBus {

    private final Queue<Event> eventQueue=new ArrayDeque<>();
    private final Thread dispatchThread=new Thread(new Runnable() {
        @Override
        public void run() {
            Queue<Event> localQueue=new ArrayDeque<>();
            while(!Thread.interrupted()) {
                synchronized (eventQueue) {
                    localQueue.addAll(eventQueue);
                    eventQueue.clear();
                }
                for(Event e:localQueue) {
                    EventType<?> type=e.getType();
                    for(EventListener listener:getListeners(type)) {
                        listener.handle(e);
                    }
                }
                synchronized (eventQueue) {
                    if(eventQueue.isEmpty()) {
                        try {
                            eventQueue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    },"Excelsea-EventBus-Impl");

    {
        dispatchThread.setDaemon(true);
        dispatchThread.start();
    }
    @Override
    public void fireEvent(Event event) {
        synchronized (eventQueue) {
            eventQueue.add(event);
            eventQueue.notifyAll();
        }
    }
}
