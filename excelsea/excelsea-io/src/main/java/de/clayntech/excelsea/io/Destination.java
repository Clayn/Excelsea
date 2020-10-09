package de.clayntech.excelsea.io;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public interface Destination {
    OutputStream open() throws IOException;

    static Destination raw(OutputStream out) {
        return ()->out;
    }

    static Destination file(File out) {
        return file(out.toPath());
    }

    static Destination file(Path out) {
        return ()-> {
            if(!Files.isRegularFile(out)||!Files.exists(out)) {
                throw new IOException("Can't open directory or not existing file: "+out);
            }
            return Files.newOutputStream(out);
        };
    }
}
