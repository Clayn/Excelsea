package de.clayntech.excelsea.io.file;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

public class ReadOnlyFileTest {
    private final File testFile;

    {
        try {
            testFile = new File(ReadOnlyTypedFile.class.getResource("/ro.file").toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException("Failed to get test file");
        }
    }

    @Test
    public void testReadFile() throws IOException {
        ReadOnlyTypedFile roFile=new ReadOnlyTypedFile(testFile) {

        };
        Assertions.assertTrue(roFile.isReadable());
        try(BufferedReader reader=new BufferedReader(new InputStreamReader(roFile.openRead()))) {
            String line=reader.readLine();
            Assertions.assertEquals("Hello World",line);
        }
    }

    @Test
    public void testWriteFile()  {
        ReadOnlyTypedFile roFile=new ReadOnlyTypedFile(testFile) {

        };
        Assertions.assertFalse(roFile.isWriteable());
        Assertions.assertThrows(RuntimeException.class, roFile::openWrite);
    }
}
