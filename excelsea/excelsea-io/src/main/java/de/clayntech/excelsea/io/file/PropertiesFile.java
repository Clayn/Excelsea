package de.clayntech.excelsea.io.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesFile extends TypedFile {
    private final Properties properties=new Properties();

    public PropertiesFile(File file) {
        super(file);
    }

    public Properties getProperties() {
        return properties;
    }

    public void load() throws IOException {
        try(InputStream in=openRead()) {
            properties.load(in);
        }
    }

    public void store() throws IOException {
        try(OutputStream out=openWrite()) {
            properties.store(out,"");
            out.flush();
        }
    }
}
