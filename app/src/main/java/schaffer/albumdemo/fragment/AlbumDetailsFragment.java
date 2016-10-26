package schaffer.albumdemo.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import schaffer.albumdemo.R;
import schaffer.albumdemo.activity.AlbumDetailsActivity;
import schaffer.albumdemo.adapter.DetailsRecyclerAdapter;
import schaffer.albumdemo.base.BaseFragment;
import schaffer.albumdemo.bean.AlbumDetail;
import schaffer.albumdemo.bean.AlbumDetailsBean;
import schaffer.albumdemo.event.DetailsDataEvent;

/**
 * Created by SchafferW on 2016/10/26.
 */

public class AlbumDetailsFragment extends BaseFragment {
    private android.support.v7.widget.RecyclerView mRecyclerDetails;

    private AlbumDetailsActivity detailsActivity;
    private AlbumDetailsBean.DataBean.AlbumDataBean albumData;
    private DetailsRecyclerAdapter mRecyclerAdapter;

    @Override
    protected void initData() {
        detailsActivity = (AlbumDetailsActivity) this.activity;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album_details, container, false);
        this.mRecyclerDetails = (RecyclerView) view.findViewById(R.id.fragment_album_details_recycler);
        mRecyclerDetails.setLayoutManager(new LinearLayoutManager(activity.getApplicationContext()));
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setData(DetailsDataEvent event) {
        albumData = event.albumDataBean;
        List<AlbumDetail> mDetailsList = new ArrayList<>();
        //专辑名称,语言,发行时间,风格,专辑介绍
        Date date = new Date(albumData.getTime());
        DateFormat dateFormat = new SimpleDateFormat("yyyy - MM - dd");
        String format = dateFormat.format(date);
        mDetailsList.add(new AlbumDetail(false, R.drawable.zhuanjimingcheng_icon, "专辑名称： ", albumData.getName()));
        mDetailsList.add(new AlbumDetail(false, R.drawable.yuyanicon, "语言： ", albumData.getLanguage()));
        mDetailsList.add(new AlbumDetail(false, R.drawable.shijianicon, "发行时间： ", format));
        mDetailsList.add(new AlbumDetail(false, R.drawable.fenggeicon, "风格： ", albumData.getStyle()));
        mDetailsList.add(new AlbumDetail(true, R.drawable.zhuanjijieshaoicon, "专辑介绍： ", albumData.getIntroduce()));
        mRecyclerAdapter = new DetailsRecyclerAdapter(mDetailsList, getContext());
        mRecyclerDetails.setAdapter(mRecyclerAdapter);
    }


}
