package lk.ijse.dep10.serialization.model.inherit1;

import java.io.Serializable;

public class C extends B implements Serializable{
    public int c = 30;

    public static final long serialVersionUID = 1l;

    public C() {
        super();
        System.out.println("C() Constructor");
    }

    public C(int a, int b, int c) {
        super(a, b);
        this.c = c;
    }

    @Override
    public String toString() {
        return "C{" +
                "c=" + c +
                ", b=" + b +
                ", a=" + a +
                '}';
    }
}
