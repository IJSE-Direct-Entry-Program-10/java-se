package lk.ijse.dep10;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalDateDemo2 {

    public static void main(String[] args) {
        String strDate = "03-12-2020";
        LocalDate date = LocalDate.parse(strDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        System.out.println("Year=" + date.getYear());
        System.out.println("Month=" + date.getMonth());
        System.out.println("Day=" + date.getDayOfMonth());
        System.out.println(date);

        String strTime = "15:13:10";
        LocalTime time = LocalTime.parse(strTime, DateTimeFormatter.ofPattern("kk:mm:ss"));
        System.out.println("Hour=" + time.getHour());
        System.out.println("Minutes=" + time.getMinute());
        System.out.println("Seconds=" + time.getSecond());
        System.out.println(time);

        String strDateTime = "2020-03-05 05:10 PM";
        LocalDateTime dateTime = LocalDateTime.
                parse(strDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a"));
        System.out.println(dateTime);
    }
}
