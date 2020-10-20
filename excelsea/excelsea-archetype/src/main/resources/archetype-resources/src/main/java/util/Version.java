package ${package}.util;

public final class Version {

    public static String getVersion() {
        return BuildInformation.getApplicationVersion();
    }
}
