package ${package};

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ${package}.fx.MainApp;

import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

public class Launcher {
    private static final Set<String> RESOURCE_PATHS=new HashSet<>(Arrays.asList("/application.info"));
    private static final Logger LOG= LoggerFactory.getLogger(Launcher.class);
    private static final Consumer<String> PRINTER=System.out::println;
    private static void checkFile(String file) {
        String path=file;
        if(!path.startsWith("/")) {
            path="/"+path;
        }
        message("Checking resource file: "+path);
        URL u=Launcher.class.getResource(path);
        message("Found: "+u);
        if(u==null) {
            throw new IllegalStateException("Resource "+path+" not available");
        }
    }
    private static void message(String mes) {
        LOG.debug(mes);
        PRINTER.accept(mes);
    }
    public static void main(String[] args) {
        RESOURCE_PATHS.stream().forEach(Launcher::checkFile);
        MainApp.main(args);
    }
}
