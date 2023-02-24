package lk.ijse.dep10.io;

import java.io.File;
import java.util.Properties;

public class EnvDemo2 {

    public static void main(String[] args) {
//        String tempDir = System.getProperty("os.arch");
//        System.out.println(tempDir);

        System.setProperty("abc", "ijse");

        Properties properties = System.getProperties();
        properties.put("dep", "dep10");

        for (Object key : properties.keySet()) {
            System.out.printf("%s=%s\n", key, properties.getProperty(key.toString()));
        }
        System.out.println("===============================");
        System.out.println(properties.getProperty("os.name"));
        properties.setProperty("os.name", "Windows");
        System.out.println(properties.getProperty("os.name"));
        properties.remove("os.name");
        System.out.println(properties.getProperty("os.name"));
        System.out.println("===============================");
        System.out.println(System.getProperty("user.home"));
        File desktopDir = new File(new File(System.getProperty("user.home")), "Desktop");
        System.out.println(desktopDir);
    }
}
