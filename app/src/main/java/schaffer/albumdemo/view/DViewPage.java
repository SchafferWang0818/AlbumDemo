package schaffer.albumdemo.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by SchafferW on 2016/10/26.
 */

public class DViewPage extends ViewPager {


    public DViewPage(Context context) {
        super(context);
    }

    public DViewPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
