package ${package}.util;

import javafx.event.Event;
import javafx.event.EventHandler;

public class EventHandlerFactory {

    public static <T extends Event>EventHandler<T> createHandler(Runnable action) {
        return (evt)->action.run();
    }
}
