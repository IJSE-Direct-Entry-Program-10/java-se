package lk.ijse.dep10.io;

import java.io.*;

public class BufferedReaderDemo {

    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 10; i++) {
            viaVanilla(i);
            viaBuffers(i);
        }
    }

    private static void viaVanilla(int attempt)throws Exception{
        File file = new File("buffered-file1.dep10");
        FileReader fis = new FileReader(file);

        char[] buffer;
        String someText = "";

        long t1 = System.nanoTime();
        while (true) {
            buffer = new char[5];
            int read = fis.read(buffer);
            if (read == -1) break;
            someText += new String(buffer, 0, read);
        }
        fis.close();
        long t2 = System.nanoTime();

        //System.out.println(someText);
        if (attempt > 1) System.out.println(String.format("V:Attempt-%d: %d", attempt, (t2- t1)));
    }

    private static void viaBuffers(int attempt)throws Exception{
        File file = new File("buffered-file2.dep10");
        FileReader fis = new FileReader(file);
        BufferedReader br = new BufferedReader(fis);

        String line;
        String someText = "";

        long t1 = System.nanoTime();
        while ((line = br.readLine()) != null) {
            someText += line;
        }
        br.close();
        long t2 = System.nanoTime();

        //System.out.println(someText);
        if (attempt > 1) System.out.println(String.format("B:Attempt-%d: %d", attempt, (t2- t1)));
    }
}
