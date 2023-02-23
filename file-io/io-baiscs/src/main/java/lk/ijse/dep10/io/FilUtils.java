package lk.ijse.dep10.io;

import java.io.File;
import java.io.IOException;

public class FilUtils {

    public static void main(String[] args) throws IOException {
        File file = new File("/home/ranjith-suranga/Desktop" + File.separator + "abc.mp4");
        System.out.println(file);
        File file1 = new File(new File("/home/ranjith-suranga/Desktop"), "abc.mp4");
        System.out.println(file1);

        System.out.println(file.getName()); // File name
        System.out.println(file.getAbsolutePath()); // File path
        file1.createNewFile();
    }
}
