package lk.ijse.dep10;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Format2 {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(localDate.toString());
        System.out.println(localTime.toString());
        System.out.println(localDateTime.toString());

        System.out.println("------------------------");

        String formattedDate = localDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        System.out.println(formattedDate);
        String formattedTime = localTime.format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
        System.out.println(formattedTime);
        String formattedDateTime = localDateTime.
                format(DateTimeFormatter.ofPattern("yyyy.MM.dd hh:mm:ss"));
        System.out.println(formattedDateTime);

    }
}
