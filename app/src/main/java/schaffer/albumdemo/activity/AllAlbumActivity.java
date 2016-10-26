package schaffer.albumdemo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import schaffer.albumdemo.R;
import schaffer.albumdemo.adapter.AllBannerGvAdapter;
import schaffer.albumdemo.base.BaseActivity;
import schaffer.albumdemo.bean.AlbumAllBean;
import schaffer.albumdemo.event.PlayEvent;
import schaffer.albumdemo.utils.LogUtils;
import schaffer.albumdemo.utils.ToastUtils;
import schaffer.albumdemo.view.SimpleSwipeRefreshLayout;

public class AllAlbumActivity extends BaseActivity {

    private android.widget.ImageView mIvPlay;
    private android.widget.GridView mGvAlbum;
    private SimpleSwipeRefreshLayout mSwipe;
    private AnimationDrawable mAnimDrawable;
    private List<AlbumAllBean.DataBean.AllAlbumBean> mBannerList = new ArrayList<>();
    private int page = 0;
    public boolean firstSwipe = true;
    private AllBannerGvAdapter mGvAdapter;
    private boolean mIsBottom;
    private boolean mIsBottomLoading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_album);
        initViews();
        initData();
        initListener();
    }

    private void initListener() {
        mGvAlbum.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                setSwipeEnable(firstVisibleItem);
                // TODO: 2016/10/25 到底部加载
                if (mGvAdapter != null) {
                    mIsBottom = mGvAlbum.getChildCount() != 0
                            && firstVisibleItem + visibleItemCount == totalItemCount
                            && mGvAlbum.getLastVisiblePosition() == mGvAlbum.getAdapter().getCount() - 1;
                    LogUtils.w("" + (mGvAlbum.getChildCount() - 1));
                    LogUtils.w("load-->" + (firstVisibleItem + visibleItemCount == totalItemCount) + "-->" + (mGvAlbum.getLastVisiblePosition() == mGvAlbum.getChildCount() - 1)
                            + mGvAlbum.getLastVisiblePosition() + "?" + (mGvAlbum.getAdapter().getCount() - 1));

                }
                //在底部的时候直接添加刷新的数据到底部
                if (mIsBottom && !mIsBottomLoading) {
                    mIsBottomLoading = true;
                    ToastUtils.shortNotify("正在加载,请稍后...");
                    loadData();
                }
            }

        });
        mGvAlbum.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int albumId = mBannerList.get(position).getId();
                Intent intent = new Intent(AllAlbumActivity.this, AlbumDetailsActivity.class);
                intent.putExtra("albumId", albumId);
                startActivity(intent);
            }
        });

        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });

    }

    private void loadData() {
        page++;
        getAllAlbum(page).enqueue(new Callback<AlbumAllBean>() {
            @Override
            public void onResponse(Call<AlbumAllBean> call, Response<AlbumAllBean> response) {
                if (response.isSuccessful() && response.errorBody() == null) {
                    AlbumAllBean allBean = response.body();
                    if (!mIsBottom) {
                        mBannerList.clear();
                    }
                    mBannerList.addAll(allBean.getData().getAllAlbum());
                    if (mGvAdapter == null) {
                        initAdapter();
                    } else {
                        mGvAdapter.notifyDataSetChanged();
                    }
                    firstSwipe = false;
                } else {
                    LogUtils.w("数据错误" + response.errorBody().toString());
                    page--;
                }
                if (mIsBottomLoading) {
                    mIsBottomLoading = false;

                }
                if (!mIsBottom) {
                    mSwipe.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<AlbumAllBean> call, Throwable t) {
                fail(t);
                if (mIsBottomLoading) {
                    mIsBottomLoading = false;
                }
            }
        });
    }

    private void setSwipeEnable(int firstVisibleItem) {
        boolean enable = false;
        if (mGvAlbum.getChildCount() != 0) {
            boolean firstVisible = firstVisibleItem == 0;
            boolean isTop = mGvAlbum.getChildAt(0).getTop() == 0;
            if (firstVisible && isTop) enable = true;
        }
        if (firstSwipe) {
            enable = true;
        }
        mSwipe.setEnabled(enable);
        LogUtils.w("下拉加载?" + enable);
    }

    private void fail(Throwable t) {
        LogUtils.w("加载数据出现错误-->" + t.getCause() + "-" + t.getMessage());
        page--;
        mSwipe.setRefreshing(false);
    }

    private void initData() {
        mSwipe.setRefreshing(true);
        page++;
        getAllAlbum(page).enqueue(new Callback<AlbumAllBean>() {
            @Override
            public void onResponse(Call<AlbumAllBean> call, Response<AlbumAllBean> response) {
                if (response.isSuccessful() && response.errorBody() == null) {
                    AlbumAllBean allBean = response.body();
                    mBannerList.addAll(allBean.getData().getAllAlbum());
                    initAdapter();
                    firstSwipe = false;
                } else {
                    LogUtils.w("数据错误" + response.errorBody().toString());
                    page--;
                }
                mSwipe.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<AlbumAllBean> call, Throwable t) {
                fail(t);
            }
        });

    }

    private void initAdapter() {
        mGvAdapter = new AllBannerGvAdapter(AllAlbumActivity.this, mBannerList);
        mGvAlbum.setAdapter(mGvAdapter);
    }

    private void initViews() {
        this.mSwipe = (SimpleSwipeRefreshLayout) findViewById(R.id.album_all_swipe);
        mSwipe.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        this.mGvAlbum = (GridView) findViewById(R.id.album_all_gv);
        this.mIvPlay = (ImageView) findViewById(R.id.album_all_toolbar_menu_play);
    }


    @Override
    public void setPlayingState(PlayEvent event) {
        super.setPlayingState(event);
        playAnim();
    }

    private void playAnim() {
        if (mIsPlaying) {
            mIvPlay.setBackgroundResource(R.drawable.animation_list_on);
            mAnimDrawable = (AnimationDrawable) mIvPlay.getBackground();
            mAnimDrawable.start();
        } else {
            mIvPlay.setBackgroundResource(R.drawable.yinyuebofang1);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            playAnim();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAnimDrawable != null) {
            mAnimDrawable.stop();
        }
    }
}
