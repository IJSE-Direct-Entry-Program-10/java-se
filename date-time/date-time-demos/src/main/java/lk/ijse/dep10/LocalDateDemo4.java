package lk.ijse.dep10;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

public class LocalDateDemo4 {

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        LocalDate localDate = today.plusDays(10);
        LocalDate localDate1 = today.minusMonths(2);

        System.out.println(today.isBefore(localDate));
        System.out.println(today.isAfter(localDate1));

        LocalDate today2 = LocalDate.of(2023, Month.MARCH, 7);
        System.out.println(today == today2);
        System.out.println(today.isEqual(today2));
        System.out.println(today.equals(today2));

        LocalTime time = LocalTime.now();
        LocalTime localTime = time.plusHours(5);
        System.out.println(localTime);
    }
}
