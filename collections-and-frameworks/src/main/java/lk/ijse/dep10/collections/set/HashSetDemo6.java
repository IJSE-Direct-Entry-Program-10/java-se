package lk.ijse.dep10.collections.set;

import java.util.HashSet;

public class HashSetDemo6 {

    public static void main(String[] args) {
        HashSet<Employee> employeeSet = new HashSet<>();
        Employee kasun = new Employee(1, "Kasun", "Kasun");
        Employee kasunClone = new Employee(1, "Kasun", "Kasun Clone");
        Employee nuwan = new Employee(2, "Nuwan", "Nuwan");
        Employee amila = new Employee(3, "Amila", "Amila");

        System.out.println("Kasun's hashcode=" + kasun.hashCode());
        System.out.println("kasunClone's hashcode=" + kasunClone.hashCode());
        System.out.println("Kasun's Bucket: " + (15 & kasun.hashCode()));
        System.out.println("Kasun Clone's Bucket: " + (15 & kasunClone.hashCode()));
        System.out.println("Kasun == Kasun Clone: " + (kasun == kasunClone));
        System.out.println("Kasun equals Kasun Clone: " + (kasun.equals(kasunClone)));

        System.out.println("-------------");
        employeeSet.add(kasun);
        employeeSet.add(nuwan);
        employeeSet.add(amila);
        employeeSet.add(kasunClone);
        System.out.println(employeeSet);
    }
}

class Employee {
    int id;
    String name;
    String nickName;

    public Employee(int id, String name, String nickName) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public int hashCode() {
        System.out.println(nickName + " hash code");
        return 10;
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println(nickName + " equal's method");
        Employee e = (Employee) obj;
        return id == e.id && name.equals(e.name);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
