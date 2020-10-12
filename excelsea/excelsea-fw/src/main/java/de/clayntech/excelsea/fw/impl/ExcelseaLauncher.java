package de.clayntech.excelsea.fw.impl;

import de.clayntech.excelsea.event.EventBus;
import de.clayntech.excelsea.fw.Excelsea;
import de.clayntech.excelsea.fw.ExcelseaContext;
import de.clayntech.excelsea.fw.fx.ExcelseaFXContext;
import de.clayntech.excelsea.fw.impl.fx.ExcelseaFXLauncher;
import de.clayntech.excelsea.log.ExcelseaLoggerFactory;
import de.clayntech.excelsea.module.ModuleManager;
import org.slf4j.Logger;

import java.util.concurrent.atomic.AtomicBoolean;

public class ExcelseaLauncher {
    private static final Logger LOG= ExcelseaLoggerFactory.getLogger();
    private static final AtomicBoolean created=new AtomicBoolean(false);
    public static ExcelseaApplication launch(ExcelseaContext context) {
        synchronized (ExcelseaLauncher.class) {
            if(context instanceof ExcelseaFXContext) {
                return ExcelseaFXLauncher.launch((ExcelseaFXContext) context);
            }
            if(created.get()) {
                return Excelsea.getApplication();
            }
            LOG.info("Creating Excelsea Application with name: {}",context.getApplicationName());
            ModuleManager manager = context.getModuleManager();
            EventBus bus = context.getEventBus();
            manager.registerModule(EventBus.class, bus);
            created.set(true);
            return new ExcelseaApplication(manager, bus,context.getApplicationName());
        }
    }
}
