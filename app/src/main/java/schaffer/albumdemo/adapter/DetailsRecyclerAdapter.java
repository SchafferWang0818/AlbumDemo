package schaffer.albumdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import schaffer.albumdemo.R;
import schaffer.albumdemo.bean.AlbumDetail;
import schaffer.albumdemo.view.DTextView;

/**
 * Created by SchafferW on 2016/10/26.
 */

public class DetailsRecyclerAdapter extends RecyclerView.Adapter<DetailsRecyclerAdapter.DetailsViewHolder> {
    List<AlbumDetail> mList;
    Context context;

    public DetailsRecyclerAdapter(List<AlbumDetail> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (!mList.get(position).dif) {
            //默认
            return 0;
        }
        return 1;
    }

    @Override
    public DetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = View.inflate(context, R.layout.item1_album_details_recycler, null);
            return new DetailsViewHolder1(view);
        } else {
            view = View.inflate(context, R.layout.item2_album_details_recycler, null);
            return new DetailsViewHolder2(view);
        }
    }

    @Override
    public void onBindViewHolder(DetailsViewHolder holder, int position) {
        AlbumDetail albumDetail = mList.get(position);
        if (holder instanceof DetailsViewHolder1) {
            ((DetailsViewHolder1) holder).title_iv.setImageResource(albumDetail.imgId);
            ((DetailsViewHolder1) holder).titleTv.setText(albumDetail.title);
            ((DetailsViewHolder1) holder).contentTv.setText(albumDetail.con);
        } else if (holder instanceof DetailsViewHolder2) {
            ((DetailsViewHolder2) holder).title_iv.setImageResource(albumDetail.imgId);
            ((DetailsViewHolder2) holder).titleTv.setText(albumDetail.title);
            ((DetailsViewHolder2) holder).contentTv.setSpacing(4);
            ((DetailsViewHolder2) holder).contentTv.setText(albumDetail.con);
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class DetailsViewHolder extends RecyclerView.ViewHolder {

        public DetailsViewHolder(View itemView) {
            super(itemView);
        }
    }

    class DetailsViewHolder1 extends DetailsViewHolder {

        private final ImageView title_iv;
        private final TextView titleTv;
        private final TextView contentTv;

        public DetailsViewHolder1(View itemView) {
            super(itemView);
            title_iv = (ImageView) itemView.findViewById(R.id.item1_details_recycler_title_iv);
            titleTv = (TextView) itemView.findViewById(R.id.item1_details_recycler_title_tv);
            contentTv = (TextView) itemView.findViewById(R.id.item1_details_recycler_content_tv);
        }
    }

    class DetailsViewHolder2 extends DetailsViewHolder {

        private final ImageView title_iv;
        private final TextView titleTv;
        private final DTextView contentTv;

        public DetailsViewHolder2(View itemView) {
            super(itemView);
            title_iv = (ImageView) itemView.findViewById(R.id.item2_details_recycler_title_iv);
            titleTv = (TextView) itemView.findViewById(R.id.item2_details_recycler_title_tv);
            contentTv = (DTextView) itemView.findViewById(R.id.item2_details_recycler_content_tv);
        }
    }

}
