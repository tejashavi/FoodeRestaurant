package com.foode.restaurant.build.api;

/**
 * Created by tamil@appoets.com on 30-08-2017.
 */




import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiInterface {


    /*-------------USER--------------------*/



    @GET("api/user/logout")
    Call<ResponseBody> logout();

}
