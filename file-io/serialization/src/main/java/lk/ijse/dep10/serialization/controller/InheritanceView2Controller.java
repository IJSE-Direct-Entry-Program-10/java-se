package lk.ijse.dep10.serialization.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import lk.ijse.dep10.serialization.model.inherit2.D;

import java.io.*;

public class InheritanceView2Controller {

    public Button btnDeserialize;
    public Button btnSerializeD;

    private File file2 = new File("file2.dep10");
    
    public void btnDeserializeOnAction(ActionEvent event) {
        try {
            FileInputStream fis = new FileInputStream(file2);
            ObjectInputStream ois = new ObjectInputStream(fis);

            D dInstance = (D) ois.readObject();
            System.out.println(dInstance);

            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnSerializeDOnAction(ActionEvent event) {
        D dInstance = new D(50, 60, 70, 80);

        try {
            FileOutputStream fos = new FileOutputStream(file2);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            System.out.println("Before Write Object(): " + oos);
            oos.writeObject(dInstance);
            System.out.println("After Write Object()");

            oos.close();
            System.out.println("Serialization Done!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
