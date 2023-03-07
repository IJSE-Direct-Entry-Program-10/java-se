package lk.ijse.dep10;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class LocalDateDemo {

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println(today);
        LocalTime now = LocalTime.now();
        System.out.println(now);
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);

        LocalDate today2 = LocalDate.of(2020, 3, 7);
        System.out.println(today2);
        LocalTime time2 = LocalTime.of(15, 10, 10);
        System.out.println(time2);
        LocalDateTime dateTime2 = LocalDateTime.of(2000, Month.APRIL, 4, 5, 10);
        System.out.println(dateTime2);
    }
}
