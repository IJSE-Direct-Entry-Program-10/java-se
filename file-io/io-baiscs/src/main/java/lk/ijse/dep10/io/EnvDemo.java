package lk.ijse.dep10.io;

import java.util.Map;

public class EnvDemo {

    public static void main(String[] args) {
//        String path = System.getenv("PATH");
//        System.out.println(path);

        Map<String, String> osEnvVariables = System.getenv();
        for (String key : osEnvVariables.keySet()) {
            System.out.printf("%s=%s\n", key, osEnvVariables.get(key));
        }

    }
}
