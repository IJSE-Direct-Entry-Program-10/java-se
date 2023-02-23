package lk.ijse.dep10.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class ReadFile2 {

    public static void main(String[] args) throws IOException {
        File file = new File("/home/ranjith-suranga/Desktop/Morbius.mp4");
        System.out.println(file.length());
        if (!file.exists()){
            System.out.println("No such file exists");
            return;
        }

        FileInputStream fis = new FileInputStream(file);

        while(true) {
            byte[] buffer = new byte[1024 * 1024 * 10]; // 10 MB
            int read = fis.read(buffer);
            if (read == -1) break;
        }

        fis.close();
        System.out.println("Read!");

    }
}
