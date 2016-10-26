package schaffer.albumdemo.event;

import schaffer.albumdemo.bean.AlbumDetailsBean;

/**
 * 已经加载完数据,Activity发送数据消息给Fragment
 */

public class DetailsDataEvent {
    public AlbumDetailsBean.DataBean.AlbumDataBean albumDataBean;


    public DetailsDataEvent(AlbumDetailsBean.DataBean.AlbumDataBean albumDataBean) {
        this.albumDataBean = albumDataBean;
    }
}
