package schaffer.albumdemo.utils;

import android.widget.Toast;

import schaffer.albumdemo.base.MyApplication;

/**
 * Created by SchafferW on 2016/10/20.
 */

public class ToastUtils {
    public static void shortNotify(String content) {
        Toast.makeText(MyApplication.app, content, Toast.LENGTH_SHORT).show();
    }
}
