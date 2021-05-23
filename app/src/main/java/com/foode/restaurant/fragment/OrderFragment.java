package com.foode.restaurant.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.foode.restaurant.R;
import com.foode.restaurant.adapter.IncomingOrder;
import com.foode.restaurant.build.api.ApiClient;
import com.foode.restaurant.build.api.ApiInterface;
import com.foode.restaurant.common.AppSettings;
import com.foode.restaurant.helper.ConnectionHelper;
import com.foode.restaurant.models.CommonModel;
import com.foode.restaurant.utils.AppUtils;
import com.foode.restaurant.view.BaseFragment;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * <p>
 * create an instance of this fragment.
 */
public class OrderFragment extends BaseFragment {


    public OrderFragment() {
        // Required empty public constructor
    }

    ConnectionHelper connectionHelper;
    SwitchMaterial switchOnOff;
    RecyclerView rvOrderList;
    TextView tvNoOrder;
    IncomingOrder incomingOrder;
    GridLayoutManager gridLayoutManager;
    ApiInterface apiInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        findViewById(view);
        return view;
    }

    void findViewById(View view) {
        connectionHelper = new ConnectionHelper(mActivity);
        rvOrderList = view.findViewById(R.id.rvOrderList);
        tvNoOrder = view.findViewById(R.id.tvNoOrder);
        switchOnOff = view.findViewById(R.id.switchOnOff);

        switchOnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (connectionHelper.isConnectingToInternet()) {
                    updateStatus("1");
                } else {
                    AppUtils.showToastSort(mActivity, getString(R.string.errorInternet));
                }
            }
        });


    }

    void updateStatus(String status) {
        HashMap<String, String> hm = new HashMap<>();
        hm.put("shop_id", AppSettings.getString(AppSettings.shopId));
        hm.put("shop_status", status);
        Call<CommonModel> commonModelCall = apiInterface.updateRestaurant(hm);
        commonModelCall.enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {
                CommonModel commonModel = response.body();
            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {

            }
        });
    }
}

