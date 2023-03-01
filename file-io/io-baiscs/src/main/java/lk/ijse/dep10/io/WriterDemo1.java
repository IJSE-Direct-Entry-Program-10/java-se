package lk.ijse.dep10.io;

import java.io.File;
import java.io.FileOutputStream;

public class WriterDemo1 {

    public static void main(String[] args) throws Exception {
        String someText = "I want to write this text into a file";
        byte[] bytes = someText.getBytes();

        File file = new File("something.dep10");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bytes);
        fos.close();
        System.out.println("Saved in the something.dep10");
    }
}
