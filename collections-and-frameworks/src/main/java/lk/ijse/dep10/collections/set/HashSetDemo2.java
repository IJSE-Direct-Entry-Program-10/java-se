package lk.ijse.dep10.collections.set;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class HashSetDemo2 {

    public static void main(String[] args) {
        List<String> cityList = Arrays.
                asList("panadura", "galle", "kandy", "colombo", "galle", "matara", "kandy");
        HashSet<String> citySet = new HashSet<>(cityList);
        System.out.println("Duplicate Exists: " + (citySet.size() < cityList.size()));
    }
}
