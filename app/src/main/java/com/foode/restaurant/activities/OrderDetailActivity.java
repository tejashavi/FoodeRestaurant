package com.foode.restaurant.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foode.restaurant.R;
import com.foode.restaurant.adapter.OrderDetailAdapter;
import com.foode.restaurant.build.api.ApiClient;
import com.foode.restaurant.build.api.ApiInterface;
import com.foode.restaurant.common.AppSettings;
import com.foode.restaurant.helper.ConnectionHelper;
import com.foode.restaurant.models.PendingOrderModel;
import com.foode.restaurant.utils.AppUtils;
import com.foode.restaurant.view.BaseActivity;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailActivity extends BaseActivity {
    TextView tvOrderId, tvDropLocation, tvUserName, tvPaymentMode, tvUserNumber;
    ImageView ivUserProfile, ivCall;
    RecyclerView rvList;

    String id = "";
    ConnectionHelper connectionHelper;
    ApiInterface apiInterface;
    String phoneNumber;
    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        AppUtils.checkPermissions(mActivity);
        if (getIntent() != null) {
            id = getIntent().getStringExtra("id");
        }
        findViewById();
    }

    void findViewById() {
        connectionHelper = new ConnectionHelper(mActivity);
        apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        tvOrderId = findViewById(R.id.tvOrderId);
        tvDropLocation = findViewById(R.id.tvDropLocation);
        tvUserName = findViewById(R.id.tvUserName);
        tvPaymentMode = findViewById(R.id.tvPaymentMode);
        tvUserNumber = findViewById(R.id.tvUserNumber);
        ivUserProfile = findViewById(R.id.ivUserProfile);
        ivCall = findViewById(R.id.ivCall);
        rvList = findViewById(R.id.rvList);
        ivBack = findViewById(R.id.ivBack);
        //  if (pendingOrderModel != null)
        //  tvOrderId.setText("#" + pendingOrderModel.getData().get(0).getUserInfo().getOrderId());

        if (connectionHelper.isConnectingToInternet()) {
            getOrderDetail();
        } else {
            AppUtils.showToastSort(mActivity, getString(R.string.errorInternet));
        }

        ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tvUserNumber.getText().toString()));
                startActivity(intent);
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    void getOrderDetail() {
        AppUtils.showRequestDialog(mActivity);
        HashMap<String, String> hm = new HashMap<>();
        hm.put("shop_id", AppSettings.getString(AppSettings.shopId));
        // hm.put("shop_id", "3");
        hm.put("order_id", id);
        Call<PendingOrderModel> pendingOrderModelCall = apiInterface.getOrderDetail(hm);
        pendingOrderModelCall.enqueue(new Callback<PendingOrderModel>() {
            @Override
            public void onResponse(Call<PendingOrderModel> call, Response<PendingOrderModel> response) {
                AppUtils.hideDialog();
                PendingOrderModel pendingOrderModel = response.body();
                if (pendingOrderModel.getStatus().equalsIgnoreCase("SUCCESS")) {
                    tvOrderId.setText("#" + id);
                    tvDropLocation.setText(pendingOrderModel.getData().get(0).getDropLocation().getMapAddress());
                    Picasso.get().load(pendingOrderModel.getData().get(0).getUserInfo().getUserProfilePic()).placeholder(R.drawable.ic_baseline_person_24).into(ivUserProfile);
                    tvUserName.setText(pendingOrderModel.getData().get(0).getUserInfo().getUserName());
                    tvPaymentMode.setText(pendingOrderModel.getData().get(0).getUserInfo().getPayment_mode());
                    tvUserNumber.setText(pendingOrderModel.getData().get(0).getUserInfo().getUser_contact_no());
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 1);
                    OrderDetailAdapter orderDetailAdapter = new OrderDetailAdapter(mActivity, pendingOrderModel.getData().get(0).getOrderInfo());
                    rvList.setLayoutManager(gridLayoutManager);
                    rvList.setAdapter(orderDetailAdapter);
                    rvList.setVisibility(View.VISIBLE);
                } else {
                    AppUtils.showToastSort(mActivity, pendingOrderModel.getMessage());
                }
            }

            @Override
            public void onFailure(Call<PendingOrderModel> call, Throwable t) {
                AppUtils.hideDialog();
            }
        });


    }

}