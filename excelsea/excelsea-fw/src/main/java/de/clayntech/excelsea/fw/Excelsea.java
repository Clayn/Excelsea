package de.clayntech.excelsea.fw;

import de.clayntech.excelsea.event.EventBus;
import de.clayntech.excelsea.log.ExcelseaLoggerFactory;
import de.clayntech.excelsea.module.ModuleManager;
import org.slf4j.Logger;

public class Excelsea {
    private static ExcelseaApplication APPLICATION;
    private static final Logger LOG= ExcelseaLoggerFactory.getLogger();

    public static void init(ExcelseaContext context) {
        synchronized (Excelsea.class) {
            if(APPLICATION!=null) {
                return;
            }
            LOG.info("Creating Excelsea Application with name: {}",context.getApplicationName());
            ModuleManager manager = context.getModuleManager();
            EventBus bus = context.getEventBus();
            manager.registerModule(EventBus.class, bus);
            APPLICATION = new ExcelseaApplication(manager, bus,context.getApplicationName());
        }
    }

    public static ExcelseaApplication getApplication() {
        return APPLICATION;
    }
}
