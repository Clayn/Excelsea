package de.clayntech.excelsea.fw.fx;

import de.clayntech.excelsea.fw.ExcelseaContext;
import javafx.application.Application;

import java.util.Objects;

public class ExcelseaFXContext extends ExcelseaContext {
    private final Class<? extends Application> applicationClass;

    public ExcelseaFXContext(String applicationName, Class<? extends Application> applicationClass) {
        super(applicationName);
        this.applicationClass = Objects.requireNonNull(applicationClass);
    }

    public Class<? extends Application> getApplicationClass() {
        return applicationClass;
    }
}
