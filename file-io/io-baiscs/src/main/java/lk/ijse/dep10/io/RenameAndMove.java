package lk.ijse.dep10.io;

import java.io.File;

public class RenameAndMove {

    public static void main(String[] args) {

        /* Rename */
//        File oldFile = new File("/home/ranjith-suranga/Desktop/abc.xml");
//        File newFile = new File(oldFile.getParentFile(), "pom.xml");
//        oldFile.renameTo(newFile);

        /* Move */
        File src = new File("/home/ranjith-suranga/Desktop/docker-desktop-4.13.1-amd64.deb");

        File desktopFolder = src.getParentFile();
        File test = new File(desktopFolder, "test/abc/edf");
        test.mkdirs();

        File target = new File("/home/ranjith-suranga/Desktop/test/abc/edf", src.getName());

        boolean result = src.renameTo(target);
        System.out.println(result);
    }
}
