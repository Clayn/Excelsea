package de.clayntech.excelsea.common.impl.i18n;

import de.clayntech.excelsea.common.i18n.LanguageManager;

import java.util.*;
import java.util.stream.Collectors;

public class LanguageManagerImpl implements LanguageManager {
    private final List<ResourceBundle> bundles=new ArrayList<>();
    private Locale language=Locale.getDefault();

    private void rebuildResourceBundles() {
        List<ResourceBundle> newBundles=bundles
                .stream()
                .map(ResourceBundle::getBaseBundleName)
                .map((base)->ResourceBundle.getBundle(base,language))
                .collect(Collectors.toList());
        bundles.clear();
        bundles.addAll(newBundles);
    }

    @Override
    public void addResourceBundle(ResourceBundle bundle) {
        bundles.add(Objects.requireNonNull(bundle));
    }

    @Override
    public String getTranslation(String key) {
        return bundles.stream()
                .filter((bundle)->bundle.containsKey(key))
                .findFirst()
                .map((bundle)->bundle.getString(key))
                .orElseThrow(()->new IllegalArgumentException("No translation found for: "+key));
    }

    @Override
    public Locale getLocale() {
        return language;
    }

    @Override
    public void setLocale(Locale locale) {
        boolean differ=locale!=null&&!locale.equals(language);
        this.language=locale==null?language:locale;
        if(differ) {
            rebuildResourceBundles();
        }
    }
}
