package schaffer.albumdemo.utils;

/**
 * Created by SchafferW on 2016/10/26.
 */

import schaffer.albumdemo.base.MyApplication;

/**
 * dp、sp 转换为 px 的工具类
 *
 * @author fxsky 2012.11.12
 */
public class DisplayUtils {

    public static int px2dip(float pxValue) {
        final float scale = MyApplication.app.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dip2px(float dipValue) {
        final float scale = MyApplication.app.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    //setTextSize
    public static int px2sp(float pxValue) {
        final float fontScale = MyApplication.app.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }


    public static int sp2px(float spValue) {
        final float fontScale = MyApplication.app.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
