package ${package}.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public interface BuildInformation {
    static String getJavaVersion() {
        return System.getProperty("java.version");
    }

    static String getApplicationVersion() {
        Logger LOG=LoggerFactory.getLogger(BuildInformation.class);
        Properties properties=new Properties();
        try(InputStream in= BuildInformation.class.getResourceAsStream("/application.info")) {
            properties.load(in);
        } catch (IOException e) {
            LOG.error("",e);
        }
        return properties.getProperty("version","Unkown");
    }

    static String getBuildTimeStamp() {
        Logger LOG=LoggerFactory.getLogger(BuildInformation.class);
        Properties properties=new Properties();
        try(InputStream in= BuildInformation.class.getResourceAsStream("/application.info")) {
            properties.load(in);
        } catch (IOException e) {
            LOG.error("",e);
        }
        return properties.getProperty("build.time","Unkown");
    }
}
