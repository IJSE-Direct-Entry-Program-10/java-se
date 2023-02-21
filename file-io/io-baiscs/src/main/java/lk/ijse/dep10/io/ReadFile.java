package lk.ijse.dep10.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class ReadFile {

    public static void main(String[] args) throws IOException {
        File file = new File("/home/ranjith-suranga/Desktop/pic.jpg");
        if (!file.exists()){
            System.out.println("Invalid file path");
            return;
        }

        FileInputStream fis = new FileInputStream(file);
//        byte[] bytes = new byte[fis.available()];
//        fis.read(bytes);

//        int read1 = fis.read();
//        int read2 = fis.read();
//        int read3 = fis.read();

        byte[] bytes = fis.readAllBytes();
        fis.close();

        System.out.println(Arrays.toString(bytes));
        System.out.println(new String(bytes));
    }

}
