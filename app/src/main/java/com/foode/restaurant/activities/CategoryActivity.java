package com.foode.restaurant.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foode.restaurant.R;
import com.foode.restaurant.adapter.CategoryAdapter;
import com.foode.restaurant.build.api.ApiClient;
import com.foode.restaurant.build.api.ApiInterface;
import com.foode.restaurant.common.AppSettings;
import com.foode.restaurant.helper.ConnectionHelper;
import com.foode.restaurant.helper.GridSpacingItemDecoration;
import com.foode.restaurant.models.CategoryModel;
import com.foode.restaurant.utils.AppUtils;
import com.foode.restaurant.view.BaseActivity;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends BaseActivity {
    RecyclerView rvList;
    TextView tvNoRecord;
    ConnectionHelper connectionHelper;
    ApiInterface apiInterface;
    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        findViewById();
    }

    void findViewById() {
        connectionHelper = new ConnectionHelper(mActivity);
        apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        rvList = findViewById(R.id.rvList);
        tvNoRecord = findViewById(R.id.tvNoRecord);
        ivBack = findViewById(R.id.ivBack);

        if (connectionHelper.isConnectingToInternet()) {
            getCategoryList();
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

    void getCategoryList() {
        AppUtils.showRequestDialog(mActivity);
        HashMap<String, String> hm = new HashMap<>();
        hm.put("shop_id", AppSettings.getString(AppSettings.shopId));
        //hm.put("shop_id", "3");
        hm.put("value", "3");
        Call<CategoryModel> categoryModelCall = apiInterface.CategoryList(hm);
        categoryModelCall.enqueue(new Callback<CategoryModel>() {
            @Override
            public void onResponse(Call<CategoryModel> call, Response<CategoryModel> response) {
                AppUtils.hideDialog();
                CategoryModel categoryModel = response.body();
                if (categoryModel.getStatus().equalsIgnoreCase("SUCCESS")) {
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 2);
                    CategoryAdapter categoryAdapter = new CategoryAdapter(mActivity, categoryModel);
                    rvList.setLayoutManager(gridLayoutManager);
                    rvList.setAdapter(categoryAdapter);
                    tvNoRecord.setVisibility(View.GONE);
                    rvList.setVisibility(View.VISIBLE);
                    rvList.addItemDecoration(new GridSpacingItemDecoration(2, 20, false));
                } else {
                    tvNoRecord.setVisibility(View.VISIBLE);
                    rvList.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<CategoryModel> call, Throwable t) {
                AppUtils.hideDialog();
            }
        });


    }

}