package lk.ijse.dep10;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class UtilDate6 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Date dob = null;

        do {
            System.out.print("Enter your DOB (yyyy-mm-dd): ");
        }while(((dob = parseDate(scanner.nextLine())) == null));

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(dob);

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.YEAR, -cal1.get(Calendar.YEAR));
        cal2.add(Calendar.MONTH, -cal1.get(Calendar.MONTH));
        cal2.add(Calendar.DATE, -cal1.get(Calendar.DATE));

        System.out.println(cal2.getTime());

        System.out.printf("Years=%s, Months=%s, Days=%s \n",
                cal2.get(Calendar.YEAR),
                cal2.get(Calendar.MONTH),
                cal2.get(Calendar.DATE));
    }

    private static Date parseDate(String date){
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
