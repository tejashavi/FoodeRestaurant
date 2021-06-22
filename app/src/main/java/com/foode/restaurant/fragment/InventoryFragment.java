package com.foode.restaurant.fragment;

import android.content.Intent;
import android.os.BaseBundle;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.foode.restaurant.R;
import com.foode.restaurant.activities.MainActivity;
import com.foode.restaurant.adapter.AllReceipe;
import com.foode.restaurant.build.api.ApiClient;
import com.foode.restaurant.build.api.ApiInterface;
import com.foode.restaurant.common.AppSettings;
import com.foode.restaurant.helper.ConnectionHelper;
import com.foode.restaurant.interfaces.UpdateInventory;
import com.foode.restaurant.models.AllRecipeModel;
import com.foode.restaurant.models.CommonModel;
import com.foode.restaurant.models.Login;
import com.foode.restaurant.utils.AppUtils;
import com.foode.restaurant.view.BaseFragment;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class InventoryFragment extends BaseFragment  {


    public InventoryFragment() {
        // Required empty public constructor
    }

    ConnectionHelper connectionHelper;
    ApiInterface apiInterface;
    RecyclerView rvList;

    UpdateInventory updateInventory;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inventory, container, false);
        findViewById(view);
        return view;
    }

    void findViewById(View view) {
        apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        connectionHelper = new ConnectionHelper(mActivity);
        rvList = view.findViewById(R.id.rvList);
        getRecipe();
    }

    void getRecipe() {
        AppUtils.hideSoftKeyboard(mActivity);
        AppUtils.showRequestDialog(mActivity);
        HashMap<String, String> hm = new HashMap<>();
        hm.put("shop_id", AppSettings.getString(AppSettings.shopId));
        Call<AllRecipeModel> allRecipe = apiInterface.getAllRecipe(hm);
        allRecipe.enqueue(new Callback<AllRecipeModel>() {
            @Override
            public void onResponse(Call<AllRecipeModel> call, Response<AllRecipeModel> response) {
                AppUtils.hideDialog();
                AllRecipeModel allRecipeModel = response.body();
                if (allRecipeModel.getStatus().equalsIgnoreCase("SUCCESS")) {
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 1);
                    AllReceipe allReceipe = new AllReceipe(mActivity, allRecipeModel, updateInventory);
                    rvList.setLayoutManager(gridLayoutManager);
                    rvList.setAdapter(allReceipe);


                } else {
                    AppUtils.showToastSort(mActivity, allRecipeModel.getMessage());
                }

            }

            @Override
            public void onFailure(Call<AllRecipeModel> call, Throwable t) {
                AppUtils.hideDialog();
            }
        });
    }


}