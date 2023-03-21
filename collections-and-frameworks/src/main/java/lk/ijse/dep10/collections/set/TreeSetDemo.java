package lk.ijse.dep10.collections.set;

import java.util.LinkedHashSet;
import java.util.TreeSet;

public class TreeSetDemo {

    public static void main(String[] args) {
        TreeSet<String> mySet = new TreeSet<>();
        mySet.add("ijse");
        mySet.add("esoft");
        mySet.add("ijse");
        mySet.add("nibm");
        mySet.add("nsbm");
        mySet.add("sliit");
        mySet.add("nsbm");
        System.out.println(mySet);
        System.out.println("--------------------");
        TreeSet<Integer> numberSet = new TreeSet<>();
        numberSet.add(50);
        numberSet.add(70);
        numberSet.add(20);
        numberSet.add(10);
        numberSet.add(15);
        System.out.println(numberSet);
    }
}
