package de.clayntech.excelsea.fw;

import de.clayntech.excelsea.fw.impl.ExcelseaApplication;
import de.clayntech.excelsea.fw.impl.ExcelseaLauncher;
import de.clayntech.excelsea.log.ExcelseaLoggerFactory;
import org.slf4j.Logger;

public class Excelsea {
    private static ExcelseaApplication APPLICATION;
    private static final Logger LOG= ExcelseaLoggerFactory.getLogger();

    public static void init(ExcelseaContext context) {
        synchronized (Excelsea.class) {
            APPLICATION= ExcelseaLauncher.launch(context);
        }
    }

    public static ExcelseaApplication getApplication() {
        return APPLICATION;
    }
}
