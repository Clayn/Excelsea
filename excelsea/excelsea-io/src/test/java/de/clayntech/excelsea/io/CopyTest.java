package de.clayntech.excelsea.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.DoubleConsumer;

public class CopyTest {
    @Test
    public void testCopyProcess() throws InterruptedException, IOException {
        byte[] srcData="HelloWorld".getBytes(StandardCharsets.UTF_8);
        byte[] destData;
        InputStream in=new ByteArrayInputStream(srcData);
        ByteArrayOutputStream out=new ByteArrayOutputStream();

        Copy copy=new Copy();
        copy.setFrom(Source.raw(in));
        copy.setTo(Destination.raw(out));

            copy.perform();
        destData=out.toByteArray();
        Assertions.assertArrayEquals(srcData,destData);
    }
}
