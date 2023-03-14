package lk.ijse.dep10.regexp;

import java.util.Scanner;

public class Demo2 {

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        while (true) {
            String contact;
            do {
                System.out.print("Enter a contact: ");
                contact = scanner.nextLine();
            } while (contact.isBlank());
            System.out.println(isValidContact(contact.strip()));
        }
    }

    private static boolean isValidContact(String input){    // +9477-1234567, 077-1234567
        return input.matches("([+](94)|0)\\d{2}-\\d{7}");

//        input = input.replaceFirst("\\+94", "0"); // -> 077-1234567
//        for (char c : input.substring(0, 3).toCharArray()) {
//            if (!Character.isDigit(c)) return false;
//        }
//        if (input.charAt(3) != '-') return false;
//        for (char c : input.substring(4, 11).toCharArray()) {
//            if (!Character.isDigit(c)) return false;
//        }
//        return true;
    }
}
