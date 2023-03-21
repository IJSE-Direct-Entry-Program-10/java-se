package lk.ijse.dep10.collections.set;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class HashSetDemo3 {

    public static void main(String[] args) {
        String something =
        "Hello, are there any duplicates here in this text? Find any duplicate words if exits";
        String[] words = something.split("\\b\\s+");
        List<String> wordList = Arrays.asList(words);
        HashSet<String> wordSet = new HashSet<>(wordList);
        System.out.println("Duplicate exits: " + (wordSet.size() < wordList.size()));
    }
}
