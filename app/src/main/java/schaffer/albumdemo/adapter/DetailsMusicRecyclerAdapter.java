package schaffer.albumdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import schaffer.albumdemo.R;
import schaffer.albumdemo.bean.AlbumDetailsBean;

/**
 * Created by SchafferW on 2016/10/26.
 */

public class DetailsMusicRecyclerAdapter extends RecyclerView.Adapter<DetailsMusicRecyclerAdapter.MusicRecyclerViewHolder> {
    List<AlbumDetailsBean.DataBean.MusicBean> musicList;
    Context ctx;
    boolean mIsMultiChoose;

    public DetailsMusicRecyclerAdapter(List<AlbumDetailsBean.DataBean.MusicBean> musicList, Context ctx, boolean mIsMultiChoose) {
        this.mIsMultiChoose = mIsMultiChoose;
        this.musicList = musicList;
        this.ctx = ctx;
    }

    @Override
    public MusicRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mIsMultiChoose) {
            //多选界面
            View view = View.inflate(ctx, R.layout.item2_album_music_recycler, null);
            return new MusicMultiChooseViewHolder(view);
        } else {
            //专辑歌曲列表
            View view = View.inflate(ctx, R.layout.item1_album_music_recycler, null);
            return new MusicListViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(MusicRecyclerViewHolder holder, final int position) {
        final AlbumDetailsBean.DataBean.MusicBean musicBean = musicList.get(position);
        if (mIsMultiChoose) {
            MusicMultiChooseViewHolder viewHolder = (MusicMultiChooseViewHolder) holder;
            viewHolder.mCbMultiChoose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // TODO: 2016/10/26 选中未选中的逻辑判断 
                }
            });
            viewHolder.mAuthorTv.setText(musicBean.getAitisatname());
            viewHolder.mNameTv.setText(musicBean.getName());
            Picasso.with(ctx).load(musicBean.getPicture()).into(viewHolder.mCoverIv);
        } else {
            MusicListViewHolder viewHolder = (MusicListViewHolder) holder;
            viewHolder.mAuthorTv.setText(musicBean.getAitisatname());
            viewHolder.mNameTv.setText(musicBean.getName());
            Picasso.with(ctx).load(musicBean.getPicture()).into(viewHolder.mCoverIv);
            viewHolder.moreIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int is_charge = musicBean.getIs_charge();
                    String song_price = musicBean.getSong_price();
                    String downloadUrl = musicBean.getUrl();
                    // TODO: 2016/10/26 点击显示dialog以便于下载,收藏,转发,添加,查看歌手操作.


                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }


    class MusicRecyclerViewHolder extends RecyclerView.ViewHolder {

        public MusicRecyclerViewHolder(View itemView) {
            super(itemView);
        }
    }

    class MusicListViewHolder extends MusicRecyclerViewHolder {

        public final ImageView mCoverIv;
        public final TextView mNameTv;
        public final TextView mAuthorTv;
        public final ImageView moreIv;

        public MusicListViewHolder(View itemView) {
            super(itemView);
            mCoverIv = (ImageView) itemView.findViewById(R.id.item1_details_music_cover_iv);
            mNameTv = (TextView) itemView.findViewById(R.id.item1_details_music_name_tv);
            mAuthorTv = (TextView) itemView.findViewById(R.id.item1_details_music_author_tv);
            moreIv = (ImageView) itemView.findViewById(R.id.item1_details_music_more_iv);
        }

    }

    class MusicMultiChooseViewHolder extends MusicRecyclerViewHolder {

        public final ImageView mCoverIv;
        public final TextView mNameTv;
        public final TextView mAuthorTv;
        public final CheckBox mCbMultiChoose;

        public MusicMultiChooseViewHolder(View itemView) {
            super(itemView);
            mCoverIv = (ImageView) itemView.findViewById(R.id.item2_details_music_cover_iv);
            mNameTv = (TextView) itemView.findViewById(R.id.item2_details_music_name_tv);
            mAuthorTv = (TextView) itemView.findViewById(R.id.item2_details_music_author_tv);
            mCbMultiChoose = (CheckBox) itemView.findViewById(R.id.item2_album_music_recycler_cb);
        }
    }

}
