package lk.ijse.dep10.serialization.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import lk.ijse.dep10.serialization.model.inherit1.B;
import lk.ijse.dep10.serialization.model.inherit1.D;

import java.io.*;

public class InheritanceViewController {

    public Button btnDeserialize;
    public Button btnSerializeD;

    private File file1 = new File("file1.dep10");
    
    public void btnDeserializeOnAction(ActionEvent event) {
        try {
            FileInputStream fis = new FileInputStream(file1);
            ObjectInputStream ois = new ObjectInputStream(fis);

            D dInstance = (D) ois.readObject();
            ois.close();

            System.out.println(dInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnSerializeDOnAction(ActionEvent event) {
        D dInstance = new D(50, 60, 70, 80);
        //B bInstance = new BNew(10, 20);

        try {
            FileOutputStream fis = new FileOutputStream(file1);
            ObjectOutputStream ois = new ObjectOutputStream(fis);

            ois.writeObject(dInstance);

            ois.close();
            System.out.println("Serialization Done!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

//class BNew extends B implements Serializable{
//    public BNew() {
//    }
//
//    public BNew(int a, int b) {
//        super(a, b);
//    }
//}