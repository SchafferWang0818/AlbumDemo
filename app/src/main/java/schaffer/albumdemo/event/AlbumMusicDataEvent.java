package schaffer.albumdemo.event;

import java.util.List;

import schaffer.albumdemo.bean.AlbumDetailsBean;

/**
 * Created by SchafferW on 2016/10/26.
 */

public class AlbumMusicDataEvent {
    public List<AlbumDetailsBean.DataBean.MusicBean> musicList;

    public AlbumMusicDataEvent(List<AlbumDetailsBean.DataBean.MusicBean> musicList) {
        this.musicList = musicList;
    }
}
