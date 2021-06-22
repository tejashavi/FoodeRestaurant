package com.foode.restaurant.build.api;

/**
 * Created by tamil@appoets.com on 30-08-2017.
 */


import com.foode.restaurant.models.AllRecipeModel;
import com.foode.restaurant.models.CategoryModel;
import com.foode.restaurant.models.CommonModel;
import com.foode.restaurant.models.ItemCountModel;
import com.foode.restaurant.models.Login;
import com.foode.restaurant.models.PendingOrderModel;
import com.foode.restaurant.models.ProductModel;
import com.foode.restaurant.models.ProfileModel;
import com.foode.restaurant.models.ShopStatusModel;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiInterface {


    /*-------------Restaurant--------------------*/

    @FormUrlEncoded
    @POST("restaurantLogin")
    Call<Login> login(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("allRecipe")
    Call<AllRecipeModel> getAllRecipe(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("updateRecipeStatus")
    Call<CommonModel> updateInventory(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("updateRestaurantStatus")
    Call<CommonModel> updateRestaurant(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("profile")
    Call<ProfileModel> getProfile(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("pendingOrder")
    Call<PendingOrderModel> getPendingOrder(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("acceptedOrder")
    Call<PendingOrderModel> getAcceptedOrder(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("prepareOrder")
    Call<PendingOrderModel> getPreparingOrder(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("cancelledOrder")
    Call<PendingOrderModel> getCancelledOrder(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("deliveredOrder")
    Call<PendingOrderModel> getDeliveredOrder(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("getPrevousOrderList")
    Call<PendingOrderModel> getPrevousOrderList(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("orderDetail")
    Call<PendingOrderModel> getOrderDetail(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("itemCount")
    Call<ItemCountModel> getItemCount(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("updateShopStatus")
    Call<ShopStatusModel> updateShopStatus(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("updateDefaultShopStatus")
    Call<ShopStatusModel> updateDefaultShopStatus(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("getProductList")
    Call<ProductModel> getProductList(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("CategoryList")
    Call<CategoryModel> CategoryList(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("updateStatusOfIncommingOrder")
    Call<CommonModel> updateStatusOfIncomingOrder(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("updateOrderTime")
    Call<CommonModel> updateOrderTime(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("markasready")
    Call<CommonModel> markAsReady(@FieldMap HashMap<String, String> params);
}
