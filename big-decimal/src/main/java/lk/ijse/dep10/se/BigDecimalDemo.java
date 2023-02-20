package lk.ijse.dep10.se;

import java.math.BigDecimal;

public class BigDecimalDemo {

    public static void main(String[] args) {
        double d1 = 0.3;
        double d2 = 0.2;
        double result = d1 - d2;
        System.out.println(result);

        BigDecimal bd1 = BigDecimal.valueOf(d1);
        BigDecimal bd2 = BigDecimal.valueOf(d2);
        BigDecimal bgResult = bd1.subtract(bd2);
        System.out.println(bgResult);
    }
}
