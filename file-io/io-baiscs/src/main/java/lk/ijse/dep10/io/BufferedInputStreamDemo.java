package lk.ijse.dep10.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class BufferedInputStreamDemo {

    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 10; i++) {
            viaVanilla(i);
            viaBuffers(i);
        }
    }

    private static void viaVanilla(int attempt)throws Exception{
        File file = new File("buffered-file1.dep10");
        FileInputStream fis = new FileInputStream(file);
        long t1 = System.nanoTime();
        byte[] bytes = fis.readAllBytes();
        fis.close();
        long t2 = System.nanoTime();
        //System.out.println(new String(bytes));
        if (attempt > 1) System.out.println(String.format("V:Attempt-%d: %d", attempt, (t2- t1)));
    }

    private static void viaBuffers(int attempt)throws Exception{
        File file = new File("buffered-file2.dep10");
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        long t1 = System.nanoTime();
        byte[] bytes = bis.readAllBytes();
        bis.close();
        long t2 = System.nanoTime();
        //System.out.println(new String(bytes));
        if (attempt > 1) System.out.println(String.format("B:Attempt-%d: %d", attempt, (t2- t1)));
    }
}
