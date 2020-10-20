package ${package}.util;

import java.io.File;

public abstract class OS {

    private static final OS INSTANCE=initOS();

    private static OS initOS() {
        return isWindows()?new WindowsOSImpl():new UnixOSImpl();
    }

    public static OS getOS() {
        return INSTANCE;
    }

    private OS() {
    }

    private static final class WindowsOSImpl extends OS {

        private WindowsOSImpl() {
            super();
        }

        @Override
        public String getUserHome() {
            return new File(System.getenv().getOrDefault("APPDATA", System.getProperty("user.home")), "Chriselix"
            ).getAbsolutePath();
        }
    }

    private static final class UnixOSImpl extends OS {
        private UnixOSImpl() {
            super();
        }

        @Override
        public String getUserHome() {
            return new File(System.getProperty("user.home"), "Chriselix"
            ).getAbsolutePath();
        }
    }


    public static boolean isWindows() {
        return System.getProperty("os.name","").toLowerCase().contains("windows");
    }

    public static String getOSUserHome() {
        return INSTANCE.getUserHome();
    }

    public static File getOSDirectory(String directory) {
        return INSTANCE.getDirectory(directory);
    }

    public abstract String getUserHome();

    public File getDirectory(String directory) { return new File(getUserHome(),directory);}
}
