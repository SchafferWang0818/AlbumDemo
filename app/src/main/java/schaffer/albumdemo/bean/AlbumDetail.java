package schaffer.albumdemo.bean;

/**
 * 是否使用了不同样式布局,图片id,标题,内容
 */

public class AlbumDetail {
    public boolean dif;
    public int imgId;
    public String title;
    public String con;

    public AlbumDetail(boolean dif, int imgId, String title, String con) {
        this.dif = dif;
        this.imgId = imgId;
        this.title = title;
        this.con = con;
    }
}
