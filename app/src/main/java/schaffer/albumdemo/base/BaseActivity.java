package schaffer.albumdemo.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;

import com.zhy.autolayout.AutoLayoutActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.http.Header;
import schaffer.albumdemo.bean.AlbumAllBean;
import schaffer.albumdemo.bean.AlbumDetailsBean;
import schaffer.albumdemo.bean.AlbumHomeBean;
import schaffer.albumdemo.event.PlayEvent;
import schaffer.albumdemo.network.RetrofitService;
import schaffer.albumdemo.network.RetrofitUtils;
import schaffer.albumdemo.utils.ToastUtils;


public class BaseActivity extends AutoLayoutActivity implements RetrofitService {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            handleMsg(msg);
        }
    };

    protected void handleMsg(Message msg) {

    }

    @Override
    public void finish() {
        super.finish();
        handler.removeCallbacksAndMessages(null);
    }

    public void back(View view) {
        finish();
    }

    @Override
    public Call<AlbumAllBean> getAllAlbum(@Header("page") int page) {
        return RetrofitUtils.getStringService().getAllAlbum(page);
    }

    @Override
    public Call<AlbumHomeBean> getHomeAlbumContent(@Header("page") int page) {
        return RetrofitUtils.getStringService().getHomeAlbumContent(page);
    }

    @Override
    public Call<AlbumDetailsBean> getAlbumDetails(@Header("id") int id) {
        return RetrofitUtils.getStringService().getAlbumDetails(id);
    }

    /**
     * 点击进入播放界面
     *
     * @param view 正在播放的按钮
     */
    public void playing(View view) {
        //点击进入播放界面
        ToastUtils.shortNotify("点击进入播放界面");
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    public boolean mIsPlaying;

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setPlayingState(PlayEvent event) {
        mIsPlaying = event.isPlaying;
    }

}
