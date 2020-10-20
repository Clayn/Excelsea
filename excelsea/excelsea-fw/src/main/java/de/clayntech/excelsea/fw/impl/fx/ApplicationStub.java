package de.clayntech.excelsea.fw.impl.fx;

import de.clayntech.excelsea.fw.Excelsea;
import de.clayntech.excelsea.fw.event.ApplicationStateEvent;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Objects;

public class ApplicationStub extends Application {

    private final Application delegate;
    public ApplicationStub(Application delegate) {
        this.delegate= Objects.requireNonNull(delegate,"ApplicationStub requires an instance of '"+Application.class.getName()+"'");
    }

    @Override
    public void init() throws Exception {
        Excelsea.getApplication().getEventBus().fireEvent(new ApplicationStateEvent(Excelsea.getApplication(),ApplicationStateEvent.FX_INIT_START));
        delegate.init();
        Excelsea.getApplication().getEventBus().fireEvent(new ApplicationStateEvent(Excelsea.getApplication(),ApplicationStateEvent.FX_INIT_DONE));
    }

    @Override
    public void start(Stage stage) throws Exception {
        delegate.start(stage);
    }

    @Override
    public void stop() throws Exception {
        delegate.stop();
    }
}
