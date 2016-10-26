package schaffer.albumdemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import schaffer.albumdemo.R;
import schaffer.albumdemo.bean.AlbumHomeBean;
import schaffer.albumdemo.base.MvcBaseAdapter;

/**
 * Created by SchafferW on 2016/10/24.
 */

public class NewLvAdapter extends MvcBaseAdapter<AlbumHomeBean.DataBean.NewDataBean> {


    public NewLvAdapter(Context ctx, List<AlbumHomeBean.DataBean.NewDataBean> list) {
        super(ctx, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(ctx, R.layout.item_album_new_lv, null);
            holder = new ViewHolder();

            holder.cover = (ImageView) convertView.findViewById(R.id.album_new_lv_item_iv);
            holder.name = (TextView) convertView.findViewById(R.id.album_new_lv_item_name);
            holder.author = (TextView) convertView.findViewById(R.id.album_new_lv_item_author);
            holder.date = (TextView) convertView.findViewById(R.id.item_hot_gv_music_date);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        AlbumHomeBean.DataBean.NewDataBean item = getItem(position);
        holder.author.setText(item.getArtistname());
        holder.name.setText(item.getName());
        holder.date.setText(getDate(getItem(position).getTime()));
        if (!isScrolling) {
            Picasso.with(ctx).load(item.getImg()).placeholder(R.drawable.zuireyinyue_def).fit().into(holder.cover);
        }
        return convertView;
    }

    static class ViewHolder {

        ImageView cover;
        TextView name;
        TextView author;
        TextView date;

    }
}
