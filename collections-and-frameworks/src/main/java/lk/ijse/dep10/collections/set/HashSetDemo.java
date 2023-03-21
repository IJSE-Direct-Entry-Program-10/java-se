package lk.ijse.dep10.collections.set;

import java.util.HashSet;

public class HashSetDemo {

    public static void main(String[] args) {
        HashSet<String> mySet = new HashSet<>();
        mySet.add("ijse");
        mySet.add("esoft");
        mySet.add("sliit");
        mySet.add("nibm");
        mySet.add("nsbm");
        mySet.add("iit");
        mySet.add("ijse");
        mySet.add("sliit");
        System.out.println(mySet.size());
        System.out.println(mySet);

        System.out.println("-------------------------");

        System.out.println(mySet.contains("nibm"));
        System.out.println(mySet.contains("mit"));

        System.out.println("-------------------------");

        for (String s : mySet) {
            System.out.println(s);
        }
    }
}
