package de.clayntech.excelsea.common.i18n;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Interface to provide translation capabilities to your application.
 * An implementation must be sure that after changing the language, all subsequent calls
 * to the {@link #getTranslation(String)} method return the translations for the new locale.
 *
 * If a resource bundle is directly added the {@link ResourceBundle#getBaseBundleName()} method must return the base
 * bundle name. This may be an issue when getting a resource bundle the conventional way so it is the best to
 * use the {@link #addResourceBundle(String)} method instead. Implementations must than ensure, that the bundle name is kept.
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
