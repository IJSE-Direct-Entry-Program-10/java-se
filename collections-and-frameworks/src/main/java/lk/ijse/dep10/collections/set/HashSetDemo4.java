package lk.ijse.dep10.collections.set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class HashSetDemo4 {

    public static void main(String[] args) {
        String something = "Find whether any duplicate characters exist in this text";
        char[] chars = something.toCharArray();
        ArrayList<String> charList = new ArrayList<>();
        for (char aChar : chars) {
            if (!Character.isSpaceChar(aChar)) charList.add(String.valueOf(aChar));
        }
        HashSet<String> charSet = new HashSet<>(charList);
        System.out.println("Duplicate exits: " + (charSet.size() < charList.size()));
    }
}
