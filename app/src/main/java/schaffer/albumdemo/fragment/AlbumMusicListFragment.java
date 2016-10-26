package schaffer.albumdemo.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import schaffer.albumdemo.R;
import schaffer.albumdemo.activity.AlbumDetailsActivity;
import schaffer.albumdemo.adapter.DetailsMusicRecyclerAdapter;
import schaffer.albumdemo.base.BaseFragment;
import schaffer.albumdemo.bean.AlbumDetailsBean;
import schaffer.albumdemo.event.AlbumMusicDataEvent;
import schaffer.albumdemo.utils.LogUtils;

/**
 * Created by SchafferW on 2016/10/26.
 */

public class AlbumMusicListFragment extends BaseFragment {


    private android.support.v7.widget.RecyclerView mRecyclerMusic;
    private android.widget.TextView mChooseTv;
    private android.widget.TextView mRandomTv;
    private AlbumDetailsActivity detailsActivity;
    private boolean mHadData;
    private List<AlbumDetailsBean.DataBean.MusicBean> musicList;
    private DetailsMusicRecyclerAdapter mMusicAdapter;

    @Override
    protected void initData() {

    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album_music_list, container, false);
        this.mChooseTv = (TextView) view.findViewById(R.id.fragment_album_music_choose_tv);
        this.mRandomTv = (TextView) view.findViewById(R.id.fragment_album_music_random_tv);
        this.mRecyclerMusic = (RecyclerView) view.findViewById(R.id.fragment_album_music_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerMusic.setLayoutManager(manager);
        mChooseTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mHadData) {
                    // TODO: 2016/10/26  进入全选界面
                }
            }
        });
        mRandomTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2016/10/26 随即播放全部
            }
        });
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setData(AlbumMusicDataEvent event) {
        LogUtils.w("list已经加载数据");
        musicList = event.musicList;
        mMusicAdapter = new DetailsMusicRecyclerAdapter(musicList, getContext(), false);
        mRecyclerMusic.setAdapter(mMusicAdapter);
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
}
