package lk.ijse.dep10.regexp;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Demo3 {

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        while (true) {
            String dob;
            do {
                System.out.print("Enter a dob (yyyy-mm-dd): ");
                dob = scanner.nextLine();
            } while (dob.isBlank());
            System.out.println(isValidDOB(dob.strip()));
        }
    }

    private static boolean isValidDOB(String input) {    // 1900<, 01-12, 01-31, 1995-1-2
        if (!input.matches("(19\\d{2}|2\\d{3})-(0[1-9]|1[0-2])-(0[1-9]|[1-2]\\d|3[0-1])")) return false;

//        if (input.length() != 10) return false;
//        String year = input.substring(0, 4);
//        String month = input.substring(5, 7);
//        String day = input.substring(8, 10);
//
//        for (char c : year.toCharArray()) {
//            if (!Character.isDigit(c)) return false;
//        }
//        if (Integer.parseInt(year) < 1900) return false;
//        if (input.charAt(4) != '-') return false;
//        for (char c : month.toCharArray()) {
//            if (!Character.isDigit(c)) return false;
//        }
//        if ((Integer.parseInt(month) < 1 || Integer.parseInt(month) > 12)) return false;
//        if (input.charAt(7) != '-') return false;
//        for (char c : day.toCharArray()) {
//            if (!Character.isDigit(c)) return false;
//        }
//        if ((Integer.parseInt(day) < 1 || Integer.parseInt(day) > 31)) return false;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            return sdf.parse(input).before(new Date());
        } catch (ParseException e) {
            return false;
        }
    }
}
