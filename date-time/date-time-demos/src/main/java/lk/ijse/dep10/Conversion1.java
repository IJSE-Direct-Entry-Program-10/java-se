package lk.ijse.dep10;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Conversion1 {

    public static void main(String[] args) throws ParseException {
        String strDate = "2021-01-01";
        String strTime = "10:15:12";
        String strDateTime = "2021-01-01 10:15:12";

        /* String -> java.util.Date (via SimpleDateFormat) */
        Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
        Date d2 = new SimpleDateFormat("kk:mm:ss").parse(strTime);
        Date d3 = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss").parse(strDateTime);

        System.out.println("Date=" + d1);
        System.out.println("Time=" + d2);
        System.out.println("DateTime=" + d3);
        System.out.println("-------------------------------");

        /* String -> java.time.LocalDate, java.time.LocalTime, java.time.LocalDateTime */
        LocalDate localDate = LocalDate.parse(strDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalTime localTime = LocalTime.parse(strTime, DateTimeFormatter.ofPattern("kk:mm:ss"));
        LocalDateTime localDateTime = LocalDateTime.
                parse(strDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss"));

        System.out.println("Date=" + localDate);
        System.out.println("Time=" + localTime);
        System.out.println("DateTime=" + localDateTime);
        System.out.println("-------------------------------");

        /* String -> java.sql.Date, java.sql.Time, java.sql.TimeStamp */
        java.sql.Date sqlDate = java.sql.Date.valueOf(strDate);     // yyyy-[m]m-[d]d
        Time sqlTime = Time.valueOf(strTime);                       // hh:mm:ss
        Timestamp sqlTimeStamp = Timestamp.valueOf(strDateTime);    // yyyy-[m]m-[d]d hh:mm:ss[.f...]

        System.out.println("Date=" + sqlDate);
        System.out.println("Time=" + sqlTime);
        System.out.println("DateTime=" + sqlTimeStamp);
        System.out.println("-------------------------------");

    }
}
