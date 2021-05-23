package com.foode.restaurant.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.foode.restaurant.R;
import com.foode.restaurant.activities.LoginActivity;
import com.foode.restaurant.build.api.ApiClient;
import com.foode.restaurant.build.api.ApiInterface;
import com.foode.restaurant.common.AppSettings;
import com.foode.restaurant.helper.ConnectionHelper;
import com.foode.restaurant.models.ProfileModel;
import com.foode.restaurant.utils.AppUtils;
import com.foode.restaurant.view.BaseFragment;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class MoreFragment extends BaseFragment {


    public MoreFragment() {
        // Required empty public constructor
    }

    ConnectionHelper connectionHelper;
    ApiInterface apiInterface;
    TextView tvName, tvEmail, tvPhone, tvDescription, tvOfferMinAmount,
            tvOfferPercent, tvEstimatedDeliveryTime, tvAddress, tvStatus;
    ImageView ivLogout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        findViewById(view);
        return view;
    }

    void findViewById(View view) {
        connectionHelper = new ConnectionHelper(mActivity);
        apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        tvName = view.findViewById(R.id.tvName);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvPhone = view.findViewById(R.id.tvPhone);
        tvDescription = view.findViewById(R.id.tvDescription);
        tvOfferMinAmount = view.findViewById(R.id.tvOfferMinAmount);
        tvOfferPercent = view.findViewById(R.id.tvOfferPercent);
        tvEstimatedDeliveryTime = view.findViewById(R.id.tvEstimatedDeliveryTime);
        tvAddress = view.findViewById(R.id.tvAddress);
        tvStatus = view.findViewById(R.id.tvStatus);
        ivLogout = view.findViewById(R.id.ivLogout);
        if (connectionHelper.isConnectingToInternet()) {
            getProfile();
        } else {
            AppUtils.showToastSort(mActivity, getString(R.string.errorInternet));
        }

        ivLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppSettings.clearSharedPreference();
                startActivity(new Intent(mActivity, LoginActivity.class));
            }
        });
    }

    void getProfile() {
        AppUtils.showRequestDialog(mActivity);
        HashMap<String, String> hm = new HashMap<>();
        hm.put("shop_id", AppSettings.getString(AppSettings.shopId));
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