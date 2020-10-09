package de.clayntech.excelsea.fw;

import de.clayntech.excelsea.event.EventBus;
import de.clayntech.excelsea.event.EventBusStub;
import de.clayntech.excelsea.module.ModuleManager;
import de.clayntech.excelsea.module.ModuleManagerStub;

public class ExcelseaContext {
    private ModuleManager moduleManager;
    private EventBus eventBus;

    public ModuleManager getModuleManager() {
        return moduleManager==null?new ModuleManagerStub():moduleManager;
    }

    public void setModuleManager(ModuleManager moduleManager) {
        this.moduleManager = moduleManager;
    }

    public EventBus getEventBus() {
        return eventBus==null?new EventBusStub():eventBus;
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }
}
