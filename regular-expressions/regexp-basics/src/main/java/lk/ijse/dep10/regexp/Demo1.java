package lk.ijse.dep10.regexp;

import java.util.Scanner;

public class Demo1 {

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        while (true) {
            String nic;
            do {
                System.out.print("Enter a nic: ");
                nic = scanner.nextLine();
            } while (nic.isBlank());
            System.out.println(isValidNIC(nic.strip()));
        }
    }

    private static boolean isValidNIC(String input){
        return input.toUpperCase().matches("\\d{9}V");
//        if (input.length() != 10) return false;
//        for (char c : input.substring(0, 9).toCharArray()) {
//            if (!Character.isDigit(c)) return false;
//        }
//        return (input.toUpperCase().endsWith("V"));
    }
}
