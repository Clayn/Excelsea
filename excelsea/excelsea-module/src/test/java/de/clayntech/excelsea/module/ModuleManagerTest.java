package de.clayntech.excelsea.module;

import de.clayntech.excelsea.module.impl.ModuleManagerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ModuleManagerTest {
    private ModuleManager manager;

    @BeforeEach
    public void setUp() {
        manager=new ModuleManagerStub();
        Assertions.assertNotNull(manager);
    }

    @Test
    public void testRegisterFactory() {
        String str="Hello World";
        Assertions.assertNotNull(manager);
        manager.register(String.class,()->str);
        String moduled=manager.getModule(String.class).get();
        Assertions.assertEquals(str,moduled);
    }

    @Test
    public void testOverwriteFactory() {
        ModuleManagerImpl manager=new ModuleManagerImpl();
        manager.setAllowingFactoryOverride(true);
        String str="Hello World";
        manager.register(String.class,()->str);
        ModuleHandle<String> handle=manager.getModule(String.class);
        String moduled=handle.get();
        Assertions.assertEquals(str,moduled);
        String string2="World Hello";
        manager.register(String.class,()->string2);
        moduled=handle.get();
        Assertions.assertEquals(string2,moduled);
    }

    @Test
    public void testOverwriteFactoryError() {
        ModuleManagerImpl manager=new ModuleManagerImpl();
        manager.setAllowingFactoryOverride(false);
        String str="Hello World";
        manager.register(String.class,()->str);
        ModuleHandle<String> handle=manager.getModule(String.class);
        String moduled=handle.get();
        Assertions.assertEquals(str,moduled);
        String string2="World Hello";
        Assertions.assertThrows(RuntimeException.class, () -> manager.registerModule(String.class,string2));
    }
}
