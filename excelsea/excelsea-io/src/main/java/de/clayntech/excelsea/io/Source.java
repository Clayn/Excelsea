package de.clayntech.excelsea.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public interface Source {
    InputStream openRead() throws IOException;

    static Source raw(InputStream in) {
        return ()->in;
    }

    static Source url(URL url) {
        return url::openStream;
    }

    static Source file(File src) {
        return file(src.toPath());
    }

    static Source file(Path src) {
        return ()-> {
            if(!Files.isRegularFile(src)||!Files.exists(src)) {
                throw new IOException("Can't open directory or file for reading: "+src);
            }
            return Files.newInputStream(src);
        };
    }
}
