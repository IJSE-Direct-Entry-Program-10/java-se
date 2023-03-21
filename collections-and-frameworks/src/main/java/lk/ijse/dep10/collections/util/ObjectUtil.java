package lk.ijse.dep10.collections.util;

public class ObjectUtil {

    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new A();

        System.out.println(a1.toString());
        System.out.println(a2.toString());
        System.out.println(a1 == a2);
    }
}

class A extends Object{
    @Override
    public int hashCode() {
        return 5;
    }
}
