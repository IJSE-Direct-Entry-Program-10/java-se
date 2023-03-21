package lk.ijse.dep10.collections.set;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class LinkedHashSetDemo {

    public static void main(String[] args) {
        LinkedHashSet<String> mySet = new LinkedHashSet<>();
        mySet.add("ijse");
        mySet.add("esoft");
        mySet.add("ijse");
        mySet.add("nibm");
        mySet.add("nsbm");
        mySet.add("sliit");
        mySet.add("nsbm");
        System.out.println(mySet);
    }
}
