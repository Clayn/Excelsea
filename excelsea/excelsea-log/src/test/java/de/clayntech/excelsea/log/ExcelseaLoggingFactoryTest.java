package de.clayntech.excelsea.log;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

public class ExcelseaLoggingFactoryTest {

    private static final class InnerClass {
        public final Logger innerLogger=ExcelseaLoggerFactory.getLogger();
    }

    @Test
    public void testGetLogger() {
        InnerClass inner=new InnerClass();
        Logger log=ExcelseaLoggerFactory.getLogger();
        Assertions.assertNotEquals(inner.innerLogger,log);
        Assertions.assertNotSame(inner.innerLogger,log);
        Assertions.assertTrue(log.getName().contains("ExcelseaLoggingFactoryTest"));
        Assertions.assertTrue(inner.innerLogger.getName().contains("InnerClass"));
    }
}
