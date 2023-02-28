package lk.ijse.dep10.serialization.model.inherit1;

import java.io.Serializable;

public class D extends C  {
    public int d = 40;

    public static final long serialVersionUID = 1l;

//    public D() {
//        System.out.println("D() Constructor");
//    }

    public D(int a, int b, int c, int d) {
        super(a, b, c);
        this.d = d;
    }

    @Override
    public String toString() {
        return "D{" +
                "d=" + d +
                ", c=" + c +
                ", b=" + b +
                ", a=" + a +
                '}';
    }
}
