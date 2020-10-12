package de.clayntech.excelsea.fw.impl.fx;

import de.clayntech.excelsea.event.EventBus;
import de.clayntech.excelsea.fw.Excelsea;
import de.clayntech.excelsea.fw.fx.ExcelseaFXContext;
import de.clayntech.excelsea.fw.impl.ExcelseaLauncher;
import de.clayntech.excelsea.log.ExcelseaLoggerFactory;
import de.clayntech.excelsea.module.ModuleManager;
import org.slf4j.Logger;

import java.util.concurrent.atomic.AtomicBoolean;

public class ExcelseaFXLauncher {
    private static final Logger LOG= ExcelseaLoggerFactory.getLogger();
    private static final AtomicBoolean created=new AtomicBoolean(false);
    public static final ExcelseaFXApplication launch(ExcelseaFXContext context) {
        synchronized (ExcelseaLauncher.class) {
            if(created.get()) {
                return (ExcelseaFXApplication) Excelsea.getApplication();
            }
            LOG.info("Creating Excelsea FX Application with name: {}",context.getApplicationName());
            ModuleManager manager = context.getModuleManager();
            EventBus bus = context.getEventBus();
            manager.registerModule(EventBus.class, bus);
            created.set(true);
            return new ExcelseaFXApplication(manager, bus,context.getApplicationName());
        }
    }
}
