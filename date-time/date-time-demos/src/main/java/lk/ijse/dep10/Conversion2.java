package lk.ijse.dep10;

public class Conversion2 {

    public static void main(String[] args) {
        java.util.Date utilDate = new java.util.Date();

        /* java.util.Date -> java.sql.Date */
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        /* java.util.Date -> java.sql.Time */
        java.sql.Time sqlTime = new java.sql.Time(utilDate.getTime());

        /* java.util.Date -> java.sql.TimeStamp */
        java.sql.Timestamp sqlTimeStamp = new java.sql.Timestamp(utilDate.getTime());

        /* java.sql.Date -> java.util.Date */
        utilDate = new java.util.Date(sqlDate.getTime());

        /* java.sql.Time -> java.util.Date */
        utilDate = new java.util.Date(sqlTime.getTime());

        /* java.sql.TimeStamp -> java.util.Date */
        utilDate = new java.util.Date(sqlTimeStamp.getTime());
    }
}
