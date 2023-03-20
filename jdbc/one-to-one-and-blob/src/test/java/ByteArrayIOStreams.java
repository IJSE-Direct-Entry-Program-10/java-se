import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class ByteArrayIOStreams {

    public static void main(String[] args) {
        byte[] bytes = {10,20,30,40,50,60};
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        int read = bis.read();
        System.out.println(read);
        read = bis.read();
        System.out.println(read);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] array = bos.toByteArray();
        System.out.println(Arrays.toString(array));
        bos.write(10);
        bos.write(20);
        bos.write(30);
        bos.write(40);
        bos.write(50);
        array = bos.toByteArray();
        System.out.println(Arrays.toString(array));
    }
}
