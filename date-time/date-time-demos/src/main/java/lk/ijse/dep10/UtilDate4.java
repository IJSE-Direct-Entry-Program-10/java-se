package lk.ijse.dep10;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UtilDate4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Date d1 = null;
        Date d2 = null;

        do {
            System.out.print("Enter date-1 (yyyy-mm-dd): ");
        }while(((d1 = parseDate(scanner.nextLine())) == null));

        do {
            System.out.print("Enter date-2 (yyyy-mm-dd): ");
        }while(((d2 = parseDate(scanner.nextLine())) == null) || d2.before(d1));

        long diffInMillis = d2.getTime() - d1.getTime();
        long days = (diffInMillis / (1000 * 60 * 60 * 24));
        System.out.println("Diff in Days: " + days);
    }

    private static Date parseDate(String date){
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
