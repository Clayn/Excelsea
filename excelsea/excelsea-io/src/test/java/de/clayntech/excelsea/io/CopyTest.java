package de.clayntech.excelsea.io;

import de.clayntech.excelsea.io.file.TypedFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.function.DoubleConsumer;

public class CopyTest {
    @Test
    public void testCopyProcess() throws InterruptedException, IOException {
        byte[] srcData="HelloWorld".getBytes(StandardCharsets.UTF_8);
        byte[] destData;
        DoubleConsumer consumer=Mockito.mock(DoubleConsumer.class);
        Mockito.doNothing().when(consumer).accept(Mockito.anyDouble());
        InputStream in=new ByteArrayInputStream(srcData);
        ByteArrayOutputStream out=new ByteArrayOutputStream();

        Copy copy=new Copy();
        copy.setFrom(Source.raw(in));
        copy.setTo(Destination.raw(out));

            copy.perform(consumer);
        destData=out.toByteArray();
        Assertions.assertArrayEquals(srcData,destData);
        Mockito.verify(consumer,Mockito.atLeast(1)).accept(Mockito.anyDouble());
    }

    @Test
    public void testCopySame() throws IOException {
        TypedFile file= Mockito.mock(TypedFile.class);
        Mockito.when(file.openRead()).thenReturn(null);
        Mockito.when(file.openWrite()).thenReturn(null);
        Copy copy=new Copy();
        copy.setFrom(file);
        copy.setTo(file);
        copy.perform();
        Mockito.verify(file,Mockito.never()).openRead();
        Mockito.verify(file,Mockito.never()).openWrite();
    }

    @Test
    public void testCopyNull() throws IOException {
        TypedFile file= Mockito.mock(TypedFile.class);
        Mockito.when(file.openRead()).thenReturn(null);
        Mockito.when(file.openWrite()).thenReturn(null);
        Copy copy=new Copy();
        copy.setFrom(null);
        copy.setTo(file);
        copy.perform();
        copy.setFrom(file);
        copy.setTo(null);
        copy.perform();
        copy.setFrom(null);
        copy.setTo(null);
        copy.perform();
        Mockito.verify(file,Mockito.never()).openRead();
        Mockito.verify(file,Mockito.never()).openWrite();
    }
}
