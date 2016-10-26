package schaffer.albumdemo.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import schaffer.albumdemo.R;
import schaffer.albumdemo.base.MvcBaseAdapter;

public class DGridView extends LinearLayout {


    private View headView;
    private MGirdView gridView;
    private MvcBaseAdapter adapter;

    public DGridView(Context context) {
        this(context, null);
    }

    public DGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        initChildren(context);
    }

    private void initChildren(Context context) {
        headView = View.inflate(context, R.layout.head_album_hot_gv, null);
        LayoutParams headLPs = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        headLPs.bottomMargin = 5;
        addView(headView, headLPs);
        gridView = new MGirdView(context);
        gridView.setNumColumns(3);
        LayoutParams gvLPs = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(gridView, gvLPs);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (onGVItemClickListener != null) {
                    onGVItemClickListener.onItemClick(parent, view, position, id);
                }
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                    if (onGVScrollListener != null) {
                        onGVScrollListener.onScrollStateChanged(view, scrollState);
                    }
                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    if (onGVScrollListener != null) {
                        onGVScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
                    }
                }
            });
        }
    }

    public void setAdapter(MvcBaseAdapter adapter) {
        this.adapter = adapter;
        gridView.setAdapter(adapter);
    }

    OnGVItemClickListener onGVItemClickListener;

    public void setOnGVItemClickListener(OnGVItemClickListener listener) {
        this.onGVItemClickListener = listener;
    }

    public interface OnGVItemClickListener {
        void onItemClick(AdapterView<?> parent, View view, int position, long id);
    }

    OnGVScrollListener onGVScrollListener;

    public void setOnGVScrollListener(OnGVScrollListener onGVScrollListener) {
        this.onGVScrollListener = onGVScrollListener;
    }

    public interface OnGVScrollListener {
        void onScrollStateChanged(AbsListView view, int scrollState);

        void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount);
    }


}
