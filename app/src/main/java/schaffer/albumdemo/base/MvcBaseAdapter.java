package schaffer.albumdemo.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by SchafferW on 2016/10/21.
 */

public abstract class MvcBaseAdapter<t> extends BaseAdapter {
    public Context ctx;
    public List<t> list;

    public boolean isScrolling = false;

    public MvcBaseAdapter(Context ctx, List<t> list) {
        this.ctx = ctx;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public t getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);

    public String getDate(long time) {
        Date date = new Date(time);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

}
