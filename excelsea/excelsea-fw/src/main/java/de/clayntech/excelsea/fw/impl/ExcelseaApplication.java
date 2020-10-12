package de.clayntech.excelsea.fw.impl;

import de.clayntech.excelsea.event.EventBus;
import de.clayntech.excelsea.module.ModuleManager;

import java.util.Objects;

public class ExcelseaApplication {
    private final ModuleManager moduleManager;
    private final EventBus eventBus;
    private final String name;

     protected ExcelseaApplication(ModuleManager moduleManager, EventBus eventBus,String name) {
        this.moduleManager = Objects.requireNonNull(moduleManager);
        this.eventBus = Objects.requireNonNull(eventBus);
        this.name=Objects.requireNonNull(name);
    }

    public String getName() {
        return name;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public EventBus getEventBus() {
        return eventBus;
    }
}
