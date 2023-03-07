package lk.ijse.dep10;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LocalDateDemo3 {

    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2020, 10, 1);
        LocalTime time = LocalTime.of(5, 7, 10);

        LocalDateTime dt1 = date.atTime(time);
        LocalDateTime dt2 = time.atDate(date);
        System.out.println(dt1);
        System.out.println(dt2);
        LocalDateTime dt3 = date.atStartOfDay();
        System.out.println(dt3);

        LocalDateTime dt4 = LocalDateTime.of(2022, 5, 3, 8, 25);
        System.out.println(dt4);
        LocalDate localDate = dt4.toLocalDate();
        System.out.println(localDate);
        LocalTime localTime = dt4.toLocalTime();
        System.out.println(localTime);


    }
}
