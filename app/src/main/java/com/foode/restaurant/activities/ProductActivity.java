package com.foode.restaurant.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foode.restaurant.R;
import com.foode.restaurant.adapter.CategoryAdapter;
import com.foode.restaurant.adapter.ProductAdapter;
import com.foode.restaurant.build.api.ApiClient;
import com.foode.restaurant.build.api.ApiInterface;
import com.foode.restaurant.common.AppSettings;
import com.foode.restaurant.helper.ConnectionHelper;
import com.foode.restaurant.helper.GridSpacingItemDecoration;
import com.foode.restaurant.models.CategoryModel;
import com.foode.restaurant.models.ProductModel;
import com.foode.restaurant.utils.AppUtils;
import com.foode.restaurant.view.BaseActivity;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends BaseActivity {
    RecyclerView rvList;
    TextView tvNoRecord;
    ConnectionHelper connectionHelper;
    ApiInterface apiInterface;
    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        findViewById();
    }

    void findViewById() {
        connectionHelper = new ConnectionHelper(mActivity);
        apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        rvList = findViewById(R.id.rvList);
        tvNoRecord = findViewById(R.id.tvNoRecord);
        ivBack = findViewById(R.id.ivBack);

        if (connectionHelper.isConnectingToInternet()) {
            getProductList();
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

    void getProductList() {
        AppUtils.showRequestDialog(mActivity);
        HashMap<String, String> hm = new HashMap<>();
       hm.put("shop_id", AppSettings.getString(AppSettings.shopId));
        //   hm.put("shop_id", "33");
        Call<ProductModel> categoryModelCall = apiInterface.getProductList(hm);
        categoryModelCall.enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {
                AppUtils.hideDialog();
                ProductModel productModel = response.body();
                if (productModel.getStatus().equalsIgnoreCase("SUCCESS")) {
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 2);
                    ProductAdapter productAdapter = new ProductAdapter(mActivity, productModel);
                    rvList.setLayoutManager(gridLayoutManager);
                    rvList.setAdapter(productAdapter);
                    tvNoRecord.setVisibility(View.GONE);
                    rvList.setVisibility(View.VISIBLE);
                    rvList.addItemDecoration(new GridSpacingItemDecoration(2, 20, false));
                } else {
                    tvNoRecord.setVisibility(View.VISIBLE);
                    rvList.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {
                AppUtils.hideDialog();
            }
        });


    }

}