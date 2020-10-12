package de.clayntech.excelsea.fw;

import de.clayntech.excelsea.event.EventBus;
import de.clayntech.excelsea.event.EventBusStub;
import de.clayntech.excelsea.module.ModuleManager;
import de.clayntech.excelsea.module.ModuleManagerStub;

import java.util.Objects;

public class ExcelseaContext {
    private ModuleManager moduleManager;
    private EventBus eventBus;
    private final String applicationName;

    public ExcelseaContext(String applicationName) {
        this.applicationName = Objects.requireNonNull(applicationName);
    }

    public String getApplicationName() {
        return applicationName;
    }

    /**
     * Returns the module manager to use for the application. If no manager was set,
     * an instance of {@link ModuleManagerStub} will be used.
     * @return the module manager for the application.
     */
    public ModuleManager getModuleManager() {
        return moduleManager==null?new ModuleManagerStub():moduleManager;
    }

    public void setModuleManager(ModuleManager moduleManager) {
        this.moduleManager = moduleManager;
    }

    /**
     * Returns the event bus to use for the application. If no event bus was set,
     * an instance of {@link EventBusStub} will be used.
     * @return the event bus for the application.
     */
    public EventBus getEventBus() {
        return eventBus==null?new EventBusStub():eventBus;
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }
}
