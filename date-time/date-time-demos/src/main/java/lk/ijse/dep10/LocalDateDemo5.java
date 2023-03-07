package lk.ijse.dep10;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;

public class LocalDateDemo5 {

    public static void main(String[] args) {
        LocalDate d1 = LocalDate.of(2023, 2, 5);
        LocalDate d2 = LocalDate.of(2020, 1, 3);

        Duration diff = Duration.between(d2.atStartOfDay(), d1.atStartOfDay());
        System.out.println(diff);
        System.out.println(diff.toDays());
        System.out.println(diff.toHours());
        System.out.println(diff.toMinutes());
        System.out.println(diff.toSeconds());
        System.out.println(diff.toMillis());
        System.out.println(diff.toNanos());

        System.out.println("-------------------");
        Period diff2 = Period.between(d2, d1);
        System.out.println("Years=" + diff2.getYears());
        System.out.println("Months=" + diff2.getMonths());
        System.out.println("Days=" + diff2.getDays());

    }
}
