package de.clayntech.excelsea.fx.i18n;

import de.clayntech.excelsea.common.impl.i18n.LanguageManagerImpl;
import de.clayntech.excelsea.log.ExcelseaLoggerFactory;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Labeled;
import org.slf4j.Logger;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LanguageManagerFX extends LanguageManagerImpl {
    private static final Logger LOG= ExcelseaLoggerFactory.getLogger();
    public static final String TRANSLATION_KEY_PROPERTY="excelsea.fx.i18n.key";
    private static final Pattern KEY_PATTERN=Pattern.compile("§(.+)§");
    private final ObjectProperty<Locale> locale=new SimpleObjectProperty<>(super.getLocale());

    public void bindTranslation(String key,StringProperty property) {
        property.bind(getTranslationProperty(key));
    }

    public void bindTranslation(String key, Labeled labeled) {
        bindTranslation(key,labeled.textProperty());
    }

    public void bindTranslation(Labeled labeled) {
        String text=labeled.getText();
        Matcher m=KEY_PATTERN.matcher(text);
        String key;
        if(m.find()) {
            key=m.group(1);
        }else {
            key=labeled.getProperties().containsKey(TRANSLATION_KEY_PROPERTY)?labeled.getProperties().get(TRANSLATION_KEY_PROPERTY).toString():null;
        }
        if(key==null) {
            LOG.warn("No translation key found in text or properties of: "+labeled);
            return;
        }
        bindTranslation(key,labeled);
    }

    public StringProperty getTranslationProperty(String key) {
        StringProperty prop=new SimpleStringProperty();
        prop.bind(Bindings.createStringBinding(() ->getTranslation(key),locale));
        return prop;
    }

    @Override
    public Locale getLocale() {
        locale.get(); //Workaround. Somehow the property won't fire the changes without this
        return super.getLocale();
    }

    @Override
    public final void setLocale(Locale locale) {
        super.setLocale(locale);
        this.locale.set(getLocale());
    }
}