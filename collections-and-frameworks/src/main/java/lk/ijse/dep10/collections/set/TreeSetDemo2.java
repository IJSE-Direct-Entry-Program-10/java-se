package lk.ijse.dep10.collections.set;

import java.util.TreeSet;

public class TreeSetDemo2 {

    public static void main(String[] args) {
        TreeSet<Customer> customerSet = new TreeSet<>();
        customerSet.add(new Customer(2, "Kasun"));
        customerSet.add(new Customer(1, "Ruwan"));
        customerSet.add(new Customer(4, "Supun"));
        customerSet.add(new Customer(3, "Nimal"));

        System.out.println(customerSet);
    }
}

class Customer implements Comparable<Customer>{
    int id;
    String name;

    @Override
    public int compareTo(Customer o) {
        if (id == o.id) return 0;
        else if (id > o.id) return 1;
        else return -1;
    }

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
