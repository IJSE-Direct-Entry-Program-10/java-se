package lk.ijse.dep10.serialization.model.inherit1;

import java.io.Serializable;

public class B extends A  {
    public int b = 20;

    public B() {
        super();
        System.out.println("B() Constructor");
    }

    public B(int a, int b) {
        super(a);
        this.b = b;
    }

    @Override
    public String toString() {
        return "B{" +
                "b=" + b +
                ", a=" + a +
                '}';
    }
}
