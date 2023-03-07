package lk.ijse.dep10;

import java.util.Calendar;
import java.util.Date;

public class UtilDate5 {

    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(2023, 2,7);
        cal.add(Calendar.MONTH, -3);
        Date d1 = cal.getTime();
        System.out.println(d1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(d1);
        Date d2 = cal2.getTime();
        System.out.println(d2);
    }
}
