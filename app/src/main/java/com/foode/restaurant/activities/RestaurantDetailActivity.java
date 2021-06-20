package com.foode.restaurant.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.foode.restaurant.R;
import com.foode.restaurant.build.api.ApiClient;
import com.foode.restaurant.build.api.ApiInterface;
import com.foode.restaurant.common.AppSettings;
import com.foode.restaurant.helper.ConnectionHelper;
import com.foode.restaurant.models.ProfileModel;
import com.foode.restaurant.utils.AppUtils;
import com.foode.restaurant.view.BaseActivity;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantDetailActivity extends BaseActivity {
    ConnectionHelper connectionHelper;
    ApiInterface apiInterface;
    TextView tvName, tvEmail, tvPhone, tvDescription, tvOfferMinAmount,
            tvOfferPercent, tvEstimatedDeliveryTime, tvAddress, tvStatus;
    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);
        findViewById();
    }

    void findViewById() {
        connectionHelper = new ConnectionHelper(mActivity);
        apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);
        tvDescription = findViewById(R.id.tvDescription);
        tvOfferMinAmount = findViewById(R.id.tvOfferMinAmount);
        tvOfferPercent = findViewById(R.id.tvOfferPercent);
        tvEstimatedDeliveryTime = findViewById(R.id.tvEstimatedDeliveryTime);
        tvAddress = findViewById(R.id.tvAddress);
        tvStatus = findViewById(R.id.tvStatus);
        ivBack = findViewById(R.id.ivBack);
        if (connectionHelper.isConnectingToInternet()) {
            getProfile();
        } else {
            AppUtils.showToastSort(mActivity, getString(R.string.errorInternet));
        }

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    void getProfile() {
        AppUtils.showRequestDialog(mActivity);
        HashMap<String, String> hm = new HashMap<>();
        hm.put("shop_id", AppSettings.getString(AppSettings.shopId));
        //hm.put("shop_id", "33");
        Call<ProfileModel> profileModelCall = apiInterface.getProfile(hm);
        profileModelCall.enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                AppUtils.hideDialog();
                ProfileModel profileModel = response.body();
                if (profileModel.getStatus().equalsIgnoreCase("SUCCESS")) {
                    tvName.setText(profileModel.getData().get(0).getName());
                    tvEmail.setText(profileModel.getData().get(0).getEmail());
                    tvPhone.setText(profileModel.getData().get(0).getPhone());
                    tvDescription.setText(profileModel.getData().get(0).getDescription());
                    tvOfferMinAmount.setText(profileModel.getData().get(0).getOfferMinAmount() + "");
                    tvOfferPercent.setText(profileModel.getData().get(0).getOfferPercent() + "");
                    tvEstimatedDeliveryTime.setText(profileModel.getData().get(0).getEstimatedDeliveryTime() + "");
                    tvAddress.setText(profileModel.getData().get(0).getAddress());
                    tvStatus.setText(profileModel.getData().get(0).getStatus());
                } else {
                    AppUtils.showToastSort(mActivity, profileModel.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                AppUtils.hideDialog();
            }
        });


    }

}