package com.foode.restaurant.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.foode.restaurant.R;
import com.foode.restaurant.activities.CategoryActivity;
import com.foode.restaurant.activities.MainActivity2;
import com.foode.restaurant.activities.ProductActivity;
import com.foode.restaurant.build.api.ApiClient;
import com.foode.restaurant.build.api.ApiInterface;
import com.foode.restaurant.common.AppSettings;
import com.foode.restaurant.helper.ConnectionHelper;
import com.foode.restaurant.models.ItemCountModel;
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
public class MenuFragment extends BaseFragment {


    public MenuFragment() {
        // Required empty public constructor
    }

    CardView cvCategory, cvItem;
    TextView tvCategoryCount, tvItemCount;
    ConnectionHelper connectionHelper;
    ApiInterface apiInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        MainActivity2.switchOnOff.setVisibility(View.GONE);
        findViewById(view);
        return view;
    }

    void findViewById(View view) {
        connectionHelper = new ConnectionHelper(mActivity);
        apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);

        tvCategoryCount = view.findViewById(R.id.tvCategoryCount);
        tvItemCount = view.findViewById(R.id.tvItemCount);
        cvCategory = view.findViewById(R.id.cvCategory);
        cvItem = view.findViewById(R.id.cvItem);

        if (connectionHelper.isConnectingToInternet()) {
            getItemCount();
        } else {
            AppUtils.showToastSort(mActivity, getString(R.string.errorInternet));
        }

        cvCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, CategoryActivity.class));
            }
        });

        cvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, ProductActivity.class));
            }
        });
    }

    void getItemCount() {
        AppUtils.showRequestDialog(mActivity);
        HashMap<String, String> hm = new HashMap<>();
         hm.put("shop_id", AppSettings.getString(AppSettings.shopId));
        // hm.put("shop_id", "3");
        hm.put("value", "3");
        Call<ItemCountModel> itemCountModelCall = apiInterface.getItemCount(hm);
        itemCountModelCall.enqueue(new Callback<ItemCountModel>() {
            @Override
            public void onResponse(Call<ItemCountModel> call, Response<ItemCountModel> response) {
                AppUtils.hideDialog();
                ItemCountModel itemCountModel = response.body();
                if (itemCountModel.getStatus().equalsIgnoreCase("SUCCESS")) {
                    tvCategoryCount.setText(itemCountModel.getCategory() + "");
                    tvItemCount.setText(itemCountModel.getProduct() + "");
                } else {
                    AppUtils.showToastSort(mActivity, itemCountModel.getMESSAGE());

                }
            }

            @Override
            public void onFailure(Call<ItemCountModel> call, Throwable t) {
                AppUtils.hideDialog();
            }
        });


    }

}