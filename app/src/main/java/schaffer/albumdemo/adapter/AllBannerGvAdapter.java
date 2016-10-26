package schaffer.albumdemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import schaffer.albumdemo.R;
import schaffer.albumdemo.bean.AlbumAllBean;
import schaffer.albumdemo.base.MvcBaseAdapter;

/**
 * Created by SchafferW on 2016/10/21.
 */

public class AllBannerGvAdapter extends MvcBaseAdapter<AlbumAllBean.DataBean.AllAlbumBean> {


    public AllBannerGvAdapter(Context ctx, List<AlbumAllBean.DataBean.AllAlbumBean> list) {
        super(ctx, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(ctx, R.layout.item_album_hot_gv, null);
            holder = new ViewHolder();
            holder.cover = (ImageView) convertView.findViewById(R.id.item_hot_gv_music_cover);
            holder.name = (TextView) convertView.findViewById(R.id.item_hot_gv_music_name);
            holder.author = (TextView) convertView.findViewById(R.id.item_hot_gv_music_author);
            holder.date = (TextView) convertView.findViewById(R.id.item_hot_gv_music_date);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String name = getItem(position).getName();
        holder.name.setText(name);
        holder.author.setText(getItem(position).getArtistname() + "");
        holder.date.setText(getDate(getItem(position).getTime()));
        if (!isScrolling) {
            Picasso.with(ctx).load(getItem(position).getImg()).into(holder.cover);
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
