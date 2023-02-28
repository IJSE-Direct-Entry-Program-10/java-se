package lk.ijse.dep10.serialization.model.inherit2;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class D extends C implements Externalizable{
    public int d = 40;

    public D() {
        System.out.println("D() Constructor");
    }

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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        //System.out.println("writeExternal: " + out);
        out.writeInt(a);
//        out.writeInt(b);
//        out.writeInt(c);
//        out.writeInt(d);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        a = in.readInt();
//        b = in.readInt();
//        c = in.readInt();
//        d = in.readInt();
    }
}
