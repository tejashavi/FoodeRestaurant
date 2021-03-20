package com.foode.restaurant.build.api;

/**
 * Created by tamil@appoets.com on 30-08-2017.
 */


import com.foode.restaurant.models.AllRecipeModel;
import com.foode.restaurant.models.Login;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiInterface {


    /*-------------Restaurant--------------------*/

    @FormUrlEncoded
    @POST("restaurantLogin")
    Call<Login> login(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("allRecipe")
    Call<AllRecipeModel> getAllRecipe(@FieldMap HashMap<String, String> params);

}
