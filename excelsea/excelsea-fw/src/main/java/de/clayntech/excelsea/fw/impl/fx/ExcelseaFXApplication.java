package de.clayntech.excelsea.fw.impl.fx;

import de.clayntech.excelsea.event.EventBus;
import de.clayntech.excelsea.fw.impl.ExcelseaApplication;
import de.clayntech.excelsea.module.ModuleManager;

public class ExcelseaFXApplication extends ExcelseaApplication {
    protected ExcelseaFXApplication(ModuleManager moduleManager, EventBus eventBus, String name) {
        super(moduleManager, eventBus, name);
    }
}
