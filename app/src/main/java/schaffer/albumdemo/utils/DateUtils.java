package schaffer.albumdemo.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by SchafferW on 2016/10/26.
 */

public class DateUtils {
    public static String getDate(long time, String format) {
        DateFormat dateFormat;
        Date date = new Date(time);
        if (format == null || format.equals("")) {
            dateFormat = new SimpleDateFormat("yyyy - MM - dd");
            return dateFormat.format(date);
        }
        dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }
}
