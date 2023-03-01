package lk.ijse.dep10.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class BufferedOutputStreamDemo {

    public static void main(String[] args)throws Exception {
        for (int i = 0; i < 10; i++) {
            viaVanilla(i + 1);
            viaBuffers(i + 1);
        }
    }

    private static void viaVanilla(int attempt) throws Exception{
        File file = new File("buffered-file1.dep10");
        FileOutputStream fos = new FileOutputStream(file);

        long t1 = System.nanoTime();
        fos.write("I want to write this text without buffered streams".getBytes());
        fos.write("I want to write this text without buffered streams".getBytes());
        fos.write("I want to write this text without buffered streams".getBytes());
        fos.write("I want to write this text without buffered streams".getBytes());
        fos.write("I want to write this text without buffered streams".getBytes());
        fos.write("I want to write this text without buffered streams".getBytes());
        fos.write("I want to write this text without buffered streams".getBytes());
        fos.close();
        long t2 = System.nanoTime();
        if (attempt > 1) System.out.println(String.format("V:Attempt-%d: %d", attempt, (t2- t1)));
    }

    private static void viaBuffers(int attempt) throws Exception{
        File file = new File("buffered-file2.dep10");
        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        long t1 = System.nanoTime();
        bos.write("I want to write this text with buffered streams".getBytes());
        bos.write("I want to write this text with buffered streams".getBytes());
        bos.write("I want to write this text with buffered streams".getBytes());
        bos.write("I want to write this text with buffered streams".getBytes());
        bos.write("I want to write this text with buffered streams".getBytes());
        bos.write("I want to write this text with buffered streams".getBytes());
        bos.write("I want to write this text with buffered streams".getBytes());
        bos.close();
        long t2 = System.nanoTime();
        if (attempt > 1) System.out.println(String.format("B:Attempt-%d: %d", attempt, (t2- t1)));
    }
}
