package de.clayntech.excelsea.module;

import de.clayntech.excelsea.module.impl.ModuleHandleImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class ModuleHandleTest {

    private ModuleManager manager;

    @BeforeEach
    public void setUp() {
        manager=new ModuleManagerStub();
        Assertions.assertNotNull(manager);
    }

    @Test
    public void testIsUsable() {
        Assertions.assertNotNull(manager);
        ModuleHandle<String> handle=manager.getModule(String.class);
        Assertions.assertFalse(handle.isUsable());
        manager.register(String.class,""::intern);
        Assertions.assertTrue(handle.isUsable());
    }

    @Test
    public void testHandleCache() {
        Assertions.assertNotNull(manager);
        AtomicInteger val=new AtomicInteger();
        manager.register(Integer.class,val::incrementAndGet);
        ModuleHandle<Integer> handle=manager.getModule(Integer.class);
        if(!(handle instanceof ModuleHandleImpl)) {
            return;
        }
        Integer created=handle.get();
        Assertions.assertEquals(created,handle.get());
    }
}
