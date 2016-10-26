package schaffer.albumdemo.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import schaffer.albumdemo.bean.AlbumAllBean;
import schaffer.albumdemo.bean.AlbumDetailsBean;
import schaffer.albumdemo.bean.AlbumHomeBean;

/**
 * Created by SchafferW on 2016/10/24.
 */
public interface RetrofitService {

    @GET("allAlbumList")
    Call<AlbumAllBean> getAllAlbum(@Header("page") int page);

    @GET("albumIndex")
    Call<AlbumHomeBean> getHomeAlbumContent(@Header("page") int page);

    //http://git.ychlink.com/api/AlbumDetail/1?id=1
    @GET("AlbumDetail/1")
    Call<AlbumDetailsBean> getAlbumDetails(@Header("id") int id);
}
