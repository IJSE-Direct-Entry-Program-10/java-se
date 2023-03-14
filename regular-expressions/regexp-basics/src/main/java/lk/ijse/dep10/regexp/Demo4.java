package lk.ijse.dep10.regexp;

import java.util.Arrays;

public class Demo4 {

    public static void main(String[] args) {
        String someText = "Here is my nic: 123456789V, Her nic is: 456789123v";
        System.out.println(isValidNIC(someText));
//        someText = someText.replaceFirst("\\d{9}[Vv]", "123");
        someText = someText.replaceAll("\\d{9}[Vv]", "xxx xxx xxx");
        System.out.println(someText);
        someText = "I want to split these words into an array";
        String[] split = someText.split("\\b");
        System.out.println(Arrays.toString(split));
    }

    private static boolean isValidNIC(String input) {
        return input.toUpperCase().matches("\\d{9}V");
    }
}
