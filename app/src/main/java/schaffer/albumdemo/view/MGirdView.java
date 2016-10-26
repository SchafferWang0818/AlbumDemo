package schaffer.albumdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by SchafferW on 2016/10/24.
 */

public class MGirdView extends GridView {


    public MGirdView(Context context) {
        super(context);
    }

    public MGirdView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MGirdView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST));
    }
}
