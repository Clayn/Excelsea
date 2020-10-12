package de.clayntech.excelsea.common.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Interface to provide translation capabilities to your application.
 * An implementation must be sure that after changing the language, all subsequent calls
 * to the {@link #getTranslation(String)} method return the translations for the new locale.
 *
 * If no translation can be found for a key the implementation may throw a {@link RuntimeException}.
 */
public interface LanguageManager {

    void addResourceBundle(ResourceBundle bundle);

    default void addResourceBundle(String baseName) {
        addResourceBundle(ResourceBundle.getBundle(baseName,getLocale()));
    }

    String getTranslation(String key);

    Locale getLocale();

    void setLocale(Locale locale);
}
