package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by oskar on 08.06.17.
 */
public class Date {
    public static java.sql.Date getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        String formatted = dateFormat.format(cal.getTime());
        return java.sql.Date.valueOf(formatted);
    }
}
