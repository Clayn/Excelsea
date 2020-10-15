package de.clayntech.excelsea.io.file;

import java.io.File;
import java.io.OutputStream;

public abstract class ReadOnlyTypedFile extends TypedFile{
    public ReadOnlyTypedFile(File file) {
        super(file);
    }

    @Override
    public final boolean isWriteable() {
        return false;
    }

    @Override
    public final OutputStream openWrite()  {
        throw new RuntimeException("Can't open read only file for writing");
    }
}
