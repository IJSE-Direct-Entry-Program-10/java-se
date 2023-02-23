package lk.ijse.dep10.io;

import java.io.File;
import java.io.IOException;

public class TempFile {

    public static void main(String[] args) throws IOException {
        File tempFile = File.createTempFile("ijse", "dep10");
        System.out.println(tempFile);
    }
}
