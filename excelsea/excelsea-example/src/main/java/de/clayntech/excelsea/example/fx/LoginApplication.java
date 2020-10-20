package de.clayntech.excelsea.example.fx;

import de.clayntech.excelsea.common.i18n.LanguageManager;
import de.clayntech.excelsea.common.i18n.LanguageManagerStub;
import de.clayntech.excelsea.fx.i18n.LanguageManagerFX;
import de.clayntech.excelsea.log.ExcelseaLoggerFactory;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.slf4j.Logger;

import java.util.Locale;

public class LoginApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Logger LOG= ExcelseaLoggerFactory.getLogger();
        LOG.debug("Starting");
        LanguageManagerFX manager=new LanguageManagerFX();
        manager.setLocale(Locale.ENGLISH);
        manager.addResourceBundle("Login");
        VBox box=new VBox(10);
        TextField nameField=new TextField();
        TextField passField=new TextField();
        Button login=new Button();
        nameField.promptTextProperty().bind(manager.getTranslationProperty("user"));
        passField.promptTextProperty().bind(manager.getTranslationProperty("password"));
        login.textProperty().bind(manager.getTranslationProperty("login"));
        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(Locale.ENGLISH.equals(manager.getLocale())) {
                    manager.setLocale(Locale.GERMAN);
                }else {
                    manager.setLocale(Locale.ENGLISH);
                }
            }
        });
        box.getChildren().addAll(nameField,passField,login);
        stage.setScene(new Scene(box));
        stage.show();
    }
}
