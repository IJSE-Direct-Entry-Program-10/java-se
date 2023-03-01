package lk.ijse.dep10.serialization.model;

import java.io.Serializable;

public class Customer extends Address implements Serializable {
    private static final long serialVersionUID = -4707805269443981395L;
    String id;
    String name;

    public Customer(String id, String name, String no, String city) {
        super(no, city);
        this.id = id;
        this.name = name;
    }

    public void print() {
        System.out.printf("id=%s, name=%s, address=%s%n", id, name, super.toString());
    }
}
