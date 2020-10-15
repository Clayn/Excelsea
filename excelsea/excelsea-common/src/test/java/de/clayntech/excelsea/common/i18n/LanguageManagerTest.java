package de.clayntech.excelsea.common.i18n;

import de.clayntech.excelsea.common.impl.i18n.LanguageManagerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;

public class LanguageManagerTest {
    private static final Logger LOG= LoggerFactory.getLogger(LanguageManagerTest.class);
    private LanguageManager manager;

    @BeforeEach
    public void setUp() {
        manager=new LanguageManagerImpl();
        manager.setLocale(Locale.ENGLISH);
        Assertions.assertNotNull(manager);
    }

    @Test
    public void testAddResourceBundle() {
        manager.addResourceBundle("i18n-1");
    }

    @Test
    public void testSetLanguageUnknown() {
        manager.addResourceBundle("i18n-1");
        Assertions.assertThrows(RuntimeException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                manager.setLocale(Locale.FRANCE);
                LOG.debug("Translation: {}",manager.getTranslation("Key"));
            }
        });
    }

    @Test
    public void testSetLanguage() {
        manager.addResourceBundle("i18n-1");
        manager.setLocale(Locale.ENGLISH);
        Assertions.assertEquals("World",manager.getTranslation("Key"));
        manager.setLocale(Locale.GERMAN);
        Assertions.assertEquals("Welt",manager.getTranslation("Key"));
    }

    @Test
    public void testAddResourceBundleErrorNotExisting() {
        Assertions.assertThrows(MissingResourceException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                manager.addResourceBundle("i18n-0");
            }
        });
    }
}
