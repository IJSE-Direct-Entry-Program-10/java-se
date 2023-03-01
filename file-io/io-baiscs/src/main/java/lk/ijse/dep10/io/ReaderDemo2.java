package lk.ijse.dep10.io;

import java.io.File;
import java.io.FileInputStream;

public class ReaderDemo2 {

    public static void main(String[] args) throws Exception {
        File file = new File("something2.dep10");
        FileInputStream fis = new FileInputStream(file);
        //String someText = "";
        StringBuilder sb = new StringBuilder();
        while (true){
            int readByte = fis.read();
            if (readByte == -1) break;
            char c = (char) readByte;
            //someText += c;
            sb.append(c);
        }
        fis.close();
        System.out.println(sb.toString());
    }
}
