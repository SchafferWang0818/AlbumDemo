package schaffer.albumdemo.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SchafferW on 2016/10/24.
 */

public class RetrofitUtils {
    private static Retrofit.Builder builder = new Retrofit.Builder();

    public static Retrofit setUrl(){
        return builder.baseUrl("http://git.ychlink.com/api/").addConverterFactory(GsonConverterFactory.create()).build();
    }



    public static RetrofitService getStringService() {
        RetrofitService service = setUrl().create(RetrofitService.class);
        return service;
    }


}
