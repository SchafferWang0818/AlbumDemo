package schaffer.albumdemo.base;

import android.app.Application;

import com.zhy.autolayout.config.AutoLayoutConifg;

/**
 * Created by SchafferW on 2016/10/20.
 */

public class MyApplication extends Application {
    public static MyApplication app;
    public static String content;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        AutoLayoutConifg.getInstance().useDeviceSize();
    }


}
