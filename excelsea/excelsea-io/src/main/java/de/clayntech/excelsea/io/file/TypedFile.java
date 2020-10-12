package de.clayntech.excelsea.io.file;

import de.clayntech.excelsea.io.Destination;
import de.clayntech.excelsea.io.Source;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Objects;

public abstract class TypedFile implements Source,Destination {
    protected final File file;

    public TypedFile(File file) {
        this.file = Objects.requireNonNull(file);
    }

    protected final File getFile() {
        return file;
    }

    @Override
    public OutputStream openWrite() throws IOException {
        return Files.newOutputStream(file.toPath());
    }

    @Override
    public InputStream openRead() throws IOException {
        return Files.newInputStream(file.toPath());
    }

    public boolean isReadable() {
        return true;
    }

    public boolean isWriteable() {
        return true;
    }
}
