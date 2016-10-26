package schaffer.albumdemo.utils;

import android.util.Log;

/**
 * Created by SchafferW on 2016/10/20.
 */

public class LogUtils {
    public static void w(String content) {
        Log.w("TAG_WARNNING", content);
    }

    public static void e(String content) {
        Log.w("TAG_ERROR", content);
    }
}
