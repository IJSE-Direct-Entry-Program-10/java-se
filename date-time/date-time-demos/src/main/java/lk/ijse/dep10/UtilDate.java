package lk.ijse.dep10;

import java.util.Calendar;
import java.util.Date;

public class UtilDate {

    public static void main(String[] args) {
        Date d1 = new Date();
        System.out.println(d1);

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(2020, 0, 1);
        //calendar.set(2020, 0, 1, 0, 0, 0);
        Date d2 = calendar.getTime();
        System.out.println(d2);

        int year = calendar.get(Calendar.YEAR);
        System.out.println("Year=" + year);
        int month = calendar.get(Calendar.MONTH);
        System.out.println("Month=" + month);
        int date = calendar.get(Calendar.DATE);
        System.out.println("Date=" + date);
        int hours = calendar.get(Calendar.HOUR);
        System.out.println("Hour=" + hours);
        int minutes = calendar.get(Calendar.MINUTE);
        System.out.println("Minutes=" + minutes);
        int seconds = calendar.get(Calendar.SECOND);
        System.out.println("Seconds=" + seconds);
        int millis = calendar.get(Calendar.MILLISECOND);
        System.out.println("Millis=" + millis);
    }
}
