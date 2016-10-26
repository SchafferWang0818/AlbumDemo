package schaffer.albumdemo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import schaffer.albumdemo.R;
import schaffer.albumdemo.adapter.HotGvAdapter;
import schaffer.albumdemo.adapter.NewLvAdapter;
import schaffer.albumdemo.base.BaseActivity;
import schaffer.albumdemo.bean.AlbumHomeBean;
import schaffer.albumdemo.event.PlayEvent;
import schaffer.albumdemo.utils.LogUtils;
import schaffer.albumdemo.view.DGridView;
import schaffer.albumdemo.view.MListView;

/**
 * 跳转到当前Activity的时候应该给一个播放状态isPlaying
 */
public class AlbumActivity extends BaseActivity {

    private List<AlbumHomeBean.DataBean.HotDataBean> mHotDataList = new ArrayList<>();
    private List<AlbumHomeBean.DataBean.NewDataBean> mNewDataList = new ArrayList<>();
    private schaffer.albumdemo.view.DGridView mGvHot;
    private MListView mLvNew;
    private HotGvAdapter mGvAdapter;
    private NewLvAdapter mLvAdapter;
    private ImageView mIvPlay;
    private AnimationDrawable mAnimDrawable;
    private int mRequestPage = 0;
    private SwipeRefreshLayout mSwipeRefresh;
    private ScrollView mSv;
    private TextView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        initViews();
        initListener();
        initData();
    }

    private void initListener() {
        mLvNew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int albumId = mNewDataList.get(position).getId();
                Intent intent = new Intent(AlbumActivity.this, AlbumDetailsActivity.class);
                intent.putExtra("albumId", albumId);
                startActivity(intent);
            }
        });
        mGvHot.setOnGVItemClickListener(new DGridView.OnGVItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //item详情
                Intent intent = new Intent(AlbumActivity.this, AlbumDetailsActivity.class);
                int albumId = mHotDataList.get(position).getId();
                intent.putExtra("albumId", albumId);
                startActivity(intent);
            }
        });
        mGvHot.setOnGVScrollListener(new DGridView.OnGVScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        mLvAdapter.isScrolling = false;
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                        mLvAdapter.isScrolling = true;
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        mLvNew.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case SCROLL_STATE_IDLE:
                        mLvAdapter.isScrolling = false;
                        break;
                    case SCROLL_STATE_FLING:
                    case SCROLL_STATE_TOUCH_SCROLL:
                        mLvAdapter.isScrolling = true;
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mSv.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    LogUtils.w("int scrollX, int scrollY--->" + scrollX + "," + scrollY);
                    if (scrollY == 0) {
                        mSwipeRefresh.setEnabled(true);
                    } else {
                        mSwipeRefresh.setEnabled(false);
                    }
                }
            });
        }

        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRequestPage++;
                getHomeAlbumContent(mRequestPage).enqueue(new Callback<AlbumHomeBean>() {
                    @Override
                    public void onResponse(Call<AlbumHomeBean> call, Response<AlbumHomeBean> response) {
                        if (response.isSuccessful() && response.errorBody() == null) {
                            AlbumHomeBean homeBean = response.body();
                            mHotDataList.clear();
                            mNewDataList.clear();
                            mHotDataList.addAll(homeBean.getData().getHotData());
                            mNewDataList.addAll(homeBean.getData().getNewData());
                            if (mGvAdapter == null) {
                                initGvAdapter();
                                initLvAdapter();
                            } else {
                                mGvAdapter.notifyDataSetChanged();
                                mLvAdapter.notifyDataSetChanged();
                            }
                        } else {
                            LogUtils.w("服务器数据为空");
                            mRequestPage--;
                            emptyView.setText("服务器出走,下拉重试");
                        }
                        mSwipeRefresh.setRefreshing(false);
                    }

                    @Override
                    public void onFailure(Call<AlbumHomeBean> call, Throwable t) {
                        LogUtils.w("加载失败的原因:" + t.getCause() + t.getMessage());
                        mRequestPage--;
                        emptyView.setText("服务器出走,下拉重试");
                        mSwipeRefresh.setRefreshing(false);
                    }
                });
            }
        });

    }

    private void initGvAdapter() {
        mGvAdapter = new HotGvAdapter(this, mHotDataList);
        mGvHot.setAdapter(mGvAdapter);
    }

    private void initLvAdapter() {
        View headView = View.inflate(this, R.layout.head_album_new_lv, null);
        headView.findViewById(R.id.loadMore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到另一个界面
                Intent intent = new Intent(AlbumActivity.this, AllAlbumActivity.class);
                startActivity(intent);

            }
        });
        mLvAdapter = new NewLvAdapter(this, mNewDataList);
        mLvNew.setAdapter(mLvAdapter);
        mLvNew.addHeaderView(headView);
    }

    private void initData() {
        mSwipeRefresh.setRefreshing(true);
        mRequestPage = 1;
        getHomeAlbumContent(mRequestPage).enqueue(new Callback<AlbumHomeBean>() {
            @Override
            public void onResponse(Call<AlbumHomeBean> call, Response<AlbumHomeBean> response) {
                if (response.isSuccessful() && response.errorBody() == null) {
                    AlbumHomeBean homeBean = response.body();
                    mHotDataList.addAll(homeBean.getData().getHotData());
                    mNewDataList.addAll(homeBean.getData().getNewData());
                    initGvAdapter();
                    initLvAdapter();
                    emptyView.setVisibility(View.GONE);
                    mSv.setVisibility(View.VISIBLE);
                } else {
                    LogUtils.w("服务器数据为空");
                    mRequestPage--;
                    emptyView.setText("服务器出走,下拉重试");
                }
                mSwipeRefresh.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<AlbumHomeBean> call, Throwable t) {
                LogUtils.w("加载失败的原因:" + t.getCause() + t.getMessage());
                mRequestPage--;
                emptyView.setText("服务器出走,下拉重试");
                mSwipeRefresh.setRefreshing(false);
            }
        });

    }

    private void initViews() {
        mGvHot = (DGridView) findViewById(R.id.album_hot_define_gv);
        mLvNew = (MListView) findViewById(R.id.album_new_def_lv);
        mIvPlay = (ImageView) findViewById(R.id.album_toolbar_menu_play);
        mSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.album_swipe);
        mSwipeRefresh.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE);
        mSv = (ScrollView) findViewById(R.id.album_sv);
        emptyView = (TextView) findViewById(R.id.album_empty_view);
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
