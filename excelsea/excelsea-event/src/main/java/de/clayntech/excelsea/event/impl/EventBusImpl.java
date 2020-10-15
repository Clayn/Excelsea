package de.clayntech.excelsea.event.impl;

import de.clayntech.excelsea.event.Event;
import de.clayntech.excelsea.event.EventBus;
import de.clayntech.excelsea.event.EventListener;
import de.clayntech.excelsea.event.EventType;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.function.Consumer;

/**
 * Simple implementation of the {@link EventBus} interface. The dispatching of events is done
 * in a separate thread that gets started when an instance is created. That thread will be a
 * daemon thread so will not prevent the application from being closed.
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class EventBusImpl extends AbstractEventProducer implements EventBus {

    private final Queue<Event> eventQueue=new ArrayDeque<>();
    private Consumer<Runnable> executor=null;
    private final Thread dispatchThread=new Thread(() -> {
        Queue<Event> localQueue=new ArrayDeque<>();
        while(!Thread.interrupted()) {
            synchronized (eventQueue) {
                localQueue.addAll(eventQueue);
                eventQueue.clear();
            }
            for(Event e:localQueue) {
                EventType<?> type=e.getType();
                for(EventListener listener:getListeners(type)) {
                    if(executor==null) {
                        listener.handle(e);
                    }else {
                        executor.accept(()->listener.handle(e));
                    }
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
    },"Excelsea-EventBus-Impl");


    public EventBusImpl(Consumer<Runnable> executor) {
        this.executor = executor;
    }

    public EventBusImpl() {
        this(null);
    }

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
