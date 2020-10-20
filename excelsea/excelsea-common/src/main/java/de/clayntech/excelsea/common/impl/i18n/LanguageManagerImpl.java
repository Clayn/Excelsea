package de.clayntech.excelsea.common.impl.i18n;

import de.clayntech.excelsea.common.i18n.LanguageManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class LanguageManagerImpl implements LanguageManager {
    private final List<ResourceBundleHandle> bundles=new ArrayList<>();
    private Locale language=Locale.getDefault();

    private static final class ResourceBundleHandle {
        private final ResourceBundle bundle;
        private final String baseBundleName;

        public ResourceBundleHandle(ResourceBundle bundle, String baseBundleName) {
            this.bundle = bundle;
            this.baseBundleName = baseBundleName;
        }

        public ResourceBundle getBundle() {
            return bundle;
        }

        public String getBaseBundleName() {
            return baseBundleName;
        }

        public ResourceBundleHandle changeLocale(Locale locale) {
            ResourceBundleHandle handle= new ResourceBundleHandle(LanguageManagerImpl.getBundle(baseBundleName,locale),baseBundleName);
            return handle;
        }
    }

    private static ResourceBundle getBundle(String name, Locale locale) {
        ResourceBundle bundle=ResourceBundle.getBundle(name,locale);
        if(!bundle.getLocale().equals(locale)) {
            bundle=ResourceBundle.getBundle(name,Locale.ROOT);
        }
        return bundle;
    }

    private List<ResourceBundle> getBundles() {
        return bundles.stream()
                .map(ResourceBundleHandle::getBundle)
                .collect(Collectors.toList());
    }

    private static final Logger LOG= LoggerFactory.getLogger(LanguageManagerImpl.class);
    private void rebuildResourceBundles() {
        List<ResourceBundleHandle> newBundles=bundles
                .stream()
                .map((handle)->handle.changeLocale(language))
                .collect(Collectors.toList());
        bundles.clear();
        bundles.addAll(newBundles);
    }

    @Override
    public void addResourceBundle(String baseName) {
        bundles.add(new ResourceBundleHandle(getBundle(baseName,language),baseName));
    }

    @Override
    public void addResourceBundle(ResourceBundle bundle) {
        if(bundle.getBaseBundleName()==null) {
            throw new IllegalArgumentException();
        }
        bundles.add(new ResourceBundleHandle(bundle,bundle.getBaseBundleName()));
    }

    @Override
    public String getTranslation(String key) {
        return getBundles().stream()
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
