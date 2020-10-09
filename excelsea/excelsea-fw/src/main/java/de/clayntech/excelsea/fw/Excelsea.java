package de.clayntech.excelsea.fw;

import de.clayntech.excelsea.event.EventBus;
import de.clayntech.excelsea.module.ModuleManager;

public class Excelsea {
    public static void init(ExcelseaContext context) {
        ModuleManager manager= context.getModuleManager();
        EventBus bus= context.getEventBus();
        manager.registerModule(EventBus.class,bus);
    }
}
