package de.clayntech.excelsea.fw.event;

import de.clayntech.excelsea.event.Event;
import de.clayntech.excelsea.event.EventType;
import de.clayntech.excelsea.fw.impl.ExcelseaApplication;

public class ApplicationStateEvent extends Event {
    public static final EventType<ApplicationStateEvent> ANY=new EventType<>(EventType.ROOT,"APP_STATE_ANY");
    public static final EventType<ApplicationStateEvent> INIT_DONE=new EventType<>(ANY,"APP_STATE_INIT_DONE");
    private final ExcelseaApplication application;

    public ApplicationStateEvent(ExcelseaApplication application,EventType<ApplicationStateEvent> type) {
        super(application,type);
        this.application=application;
    }

    public ExcelseaApplication getApplication() {
        return application;
    }
}
