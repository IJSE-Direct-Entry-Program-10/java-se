package lk.ijse.dep10.serialization.model;

import java.io.Serializable;
import java.util.ArrayList;

public class PersonInfo implements Serializable {
    private String name;
    private ArrayList<String> contacts;

    public PersonInfo() {
    }

    public PersonInfo(String name, ArrayList<String> contacts) {
        this.name = name;
        this.contacts = contacts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<String> contacts) {
        this.contacts = contacts;
    }
}
