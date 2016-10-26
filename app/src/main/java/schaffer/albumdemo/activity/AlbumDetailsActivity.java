package schaffer.albumdemo.activity;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import schaffer.albumdemo.R;
import schaffer.albumdemo.adapter.DetailsPageFragmentAdapter;
import schaffer.albumdemo.base.BaseActivity;
import schaffer.albumdemo.bean.AlbumDetailsBean;
import schaffer.albumdemo.event.AlbumMusicDataEvent;
import schaffer.albumdemo.event.DetailsDataEvent;
import schaffer.albumdemo.fragment.AlbumDetailsFragment;
import schaffer.albumdemo.fragment.AlbumMusicListFragment;
import schaffer.albumdemo.utils.DateUtils;
import schaffer.albumdemo.utils.LogUtils;
import schaffer.albumdemo.utils.ToastUtils;

public class AlbumDetailsActivity extends BaseActivity {

    private android.widget.ImageView mIvHeadBg;
    private de.hdodenhof.circleimageview.CircleImageView mIvHead;
    private android.widget.TextView mTvTime;
    private android.widget.TextView mTvAuthor;
    private android.widget.TextView mTvAlbumName;
    private NestedScrollView mNsv;
    /*右上角的播放图标,需要得到是否正在播放的内容然后查看是否播放帧动画*/
    private android.widget.ImageView mIvPlay;
    private android.support.v4.view.ViewPager mVpGroup;
    private AlbumDetailsBean.DataBean.AlbumDataBean albumData;
    private List<AlbumDetailsBean.DataBean.MusicBean> musicList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_details_design);
        initViews();
        initData();
        initListener();
    }

    private void initListener() {
        mVpGroup.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mNsv.post(new Runnable() {
                    @Override
                    public void run() {
                        mNsv.scrollTo(0, 0);
                    }
                });
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 如果在点击了专辑的情况下,应该传入一个数据,然后对应加载
     * 假数据:直接在
     */
    private void initData() {
        int mId = getIntent().getIntExtra("albumId", 0);
        if (mId == 0) {
            ToastUtils.shortNotify("专辑ID信息获取失败");
            return;
        }
//        mId = 1;//假数据
        getAlbumDetails(mId).enqueue(new Callback<AlbumDetailsBean>() {
            @Override
            public void onResponse(Call<AlbumDetailsBean> call, Response<AlbumDetailsBean> response) {
                if (response.isSuccessful() && response.errorBody() == null) {
                    AlbumDetailsBean detailsBean = response.body();
                    albumData = detailsBean.getData().getAlbumData();
                    musicList.addAll(detailsBean.getData().getMusic());
                    EventBus.getDefault().post(new DetailsDataEvent(albumData));
                    EventBus.getDefault().post(new AlbumMusicDataEvent(musicList));
                    //数据的处理
                    headDataInflate();
                }
            }

            @Override
            public void onFailure(Call<AlbumDetailsBean> call, Throwable t) {
                ToastUtils.shortNotify("专辑数据加载失败");
                LogUtils.w(t.getMessage() + "-" + t.getCause());
            }
        });

    }

    private void headDataInflate() {
        String albumName = albumData.getName();
        String headUrl = albumData.getHeadimg();
        String authorName = albumData.getUsername();
        String bg = albumData.getBannerimg();
        long time = albumData.getTime();
        mTvAlbumName.setText(albumName);
        mTvAuthor.setText(authorName);
        String text = DateUtils.getDate(time, "") + "发布";
        mTvTime.setText(text);
        Picasso.with(AlbumDetailsActivity.this).load(headUrl).into(mIvHead);
        Picasso.with(AlbumDetailsActivity.this).load(bg).into(mIvHeadBg);

    }

    private void initViews() {
        this.mVpGroup = (ViewPager) findViewById(R.id.album_details_design_fl);
        TabLayout mTab = (TabLayout) findViewById(R.id.album_details_tab);
        this.mIvPlay = (ImageView) findViewById(R.id.album_details_play_iv);
        this.mTvAlbumName = (TextView) findViewById(R.id.album_details_name_tv);
        this.mTvAuthor = (TextView) findViewById(R.id.album_details_author_tv);
        this.mTvTime = (TextView) findViewById(R.id.album_details_time_tv);
        this.mIvHead = (CircleImageView) findViewById(R.id.album_details_head_iv);
        this.mIvHeadBg = (ImageView) findViewById(R.id.iv_news_detail);
        mNsv = (NestedScrollView) findViewById(R.id.album_details_design_sv);
        List<Fragment> mFragmentList = new ArrayList<>();
        AlbumMusicListFragment musicListFragment = new AlbumMusicListFragment();
        AlbumDetailsFragment detailsFragment = new AlbumDetailsFragment();
        mFragmentList.add(musicListFragment);
        mFragmentList.add(detailsFragment);
        mVpGroup.setAdapter(new DetailsPageFragmentAdapter(getSupportFragmentManager(), mFragmentList, this));
        mTab.setupWithViewPager(mVpGroup);
    }


}
