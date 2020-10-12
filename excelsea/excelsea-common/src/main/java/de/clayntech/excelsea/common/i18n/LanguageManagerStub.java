package de.clayntech.excelsea.common.i18n;

import de.clayntech.excelsea.common.Stub;
import de.clayntech.excelsea.common.impl.i18n.LanguageManagerImpl;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageManagerStub extends Stub<LanguageManager> implements LanguageManager {

    public LanguageManagerStub() {
        this(new LanguageManagerImpl());
    }
    public LanguageManagerStub(LanguageManager delegate) {
        super(delegate);
    }

    public void addResourceBundle(ResourceBundle bundle) {
        delegate.addResourceBundle(bundle);
    }

    public void addResourceBundle(String baseName) {
        delegate.addResourceBundle(baseName);
    }

    public String getTranslation(String key) {
        return delegate.getTranslation(key);
    }

    public Locale getLocale() {
        return delegate.getLocale();
    }

    public void setLocale(Locale locale) {
        delegate.setLocale(locale);
    }
}
