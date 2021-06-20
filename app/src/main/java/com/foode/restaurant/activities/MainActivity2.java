package com.foode.restaurant.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.foode.restaurant.R;
import com.foode.restaurant.build.api.ApiClient;
import com.foode.restaurant.build.api.ApiInterface;
import com.foode.restaurant.common.AppSettings;
import com.foode.restaurant.fragments.AllOrderFragment;
import com.foode.restaurant.fragments.AppSettingFragment;
import com.foode.restaurant.fragments.MenuFragment;
import com.foode.restaurant.fragments.SaleFragment;
import com.foode.restaurant.helper.ConnectionHelper;
import com.foode.restaurant.models.ShopStatusModel;
import com.foode.restaurant.utils.AppUtils;
import com.foode.restaurant.view.BaseActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer_layout;
    NavigationView nav_view;
    ImageView ivMenu;
    public static Switch switchOnOff;
    ConnectionHelper connectionHelper;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        findViewById();
    }

    void findViewById() {
        connectionHelper = new ConnectionHelper(mActivity);
        apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        drawer_layout = findViewById(R.id.drawer_layout);
        nav_view = findViewById(R.id.nav_view);
        ivMenu = findViewById(R.id.ivMenu);
        switchOnOff = findViewById(R.id.switchOnOff);

        nav_view.setNavigationItemSelectedListener(this);

        loadFragment(new AllOrderFragment());

        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer_layout.openDrawer(GravityCompat.START);
            }
        });
        if (connectionHelper.isConnectingToInternet()) {
            getShopStatus();
        } else {
            AppUtils.showToastSort(mActivity, getString(R.string.errorInternet));
        }


        switchOnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (connectionHelper.isConnectingToInternet()) {
                    updateShopStatus();
                } else {
                    AppUtils.showToastSort(mActivity, getString(R.string.errorInternet));
                }
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Order) {
            loadFragment(new AllOrderFragment());
        } else if (id == R.id.nav_Sale) {
            loadFragment(new SaleFragment());
        } else if (id == R.id.nav_Menu) {
            loadFragment(new MenuFragment());
        } else if (id == R.id.nav_AppSetting) {
            loadFragment(new AppSettingFragment());
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameMain, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        drawer_layout.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AllOrderFragment fragment;
            fragment = (AllOrderFragment) getSupportFragmentManager().findFragmentByTag("MYFRAGMENT");
            if (fragment == null) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameMain, new AllOrderFragment(), "MYFRAGMENT");
                fragmentTransaction.addToBackStack("MYFRAGMENT").commit();
            } else {
                if (fragment.isVisible()) {
                    Intent a = new Intent(Intent.ACTION_MAIN);
                    a.addCategory(Intent.CATEGORY_HOME);
                    a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(a);
                } else {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frameMain, new AllOrderFragment(), "MYFRAGMENT");
                    fragmentTransaction.addToBackStack("MYFRAGMENT").commit();
                }
            }
        }
    }

    void updateShopStatus() {
        AppUtils.showRequestDialog(mActivity);
        HashMap<String, String> hm = new HashMap<>();
         hm.put("shop_id", AppSettings.getString(AppSettings.shopId));
        //hm.put("shop_id", "3");
        Call<ShopStatusModel> shopStatusModelCall = apiInterface.updateShopStatus(hm);
        shopStatusModelCall.enqueue(new Callback<ShopStatusModel>() {
            @Override
            public void onResponse(Call<ShopStatusModel> call, Response<ShopStatusModel> response) {
                AppUtils.hideDialog();
                ShopStatusModel shopStatusModel = response.body();
                if (shopStatusModel.getStatus().equalsIgnoreCase("SUCCESS")) {
                    if (shopStatusModel.getShopStatus().equalsIgnoreCase("active")) {
                        switchOnOff.setChecked(true);
                    } else {
                        switchOnOff.setChecked(false);
                    }
                    AppUtils.showToastSort(mActivity, shopStatusModel.getMessage());

                } else {
                    AppUtils.showToastSort(mActivity, shopStatusModel.getMessage());

                }
            }

            @Override
            public void onFailure(Call<ShopStatusModel> call, Throwable t) {
                AppUtils.hideDialog();
            }
        });


    }

    void getShopStatus() {
        // AppUtils.showRequestDialog(mActivity);
        HashMap<String, String> hm = new HashMap<>();
        // hm.put("shop_id", AppSettings.getString(AppSettings.shopId));
        hm.put("shop_id", "3");
        Call<ShopStatusModel> shopStatusModelCall = apiInterface.updateDefaultShopStatus(hm);
        shopStatusModelCall.enqueue(new Callback<ShopStatusModel>() {
            @Override
            public void onResponse(Call<ShopStatusModel> call, Response<ShopStatusModel> response) {
                AppUtils.hideDialog();
                ShopStatusModel shopStatusModel = response.body();
                if (shopStatusModel.getStatus().equalsIgnoreCase("SUCCESS")) {
                    if (shopStatusModel.getShopStatus().equalsIgnoreCase("active")) {
                        switchOnOff.setChecked(true);
                    } else {
                        switchOnOff.setChecked(false);
                    }


                } else {
                    AppUtils.showToastSort(mActivity, shopStatusModel.getMessage());

                }
            }

            @Override
            public void onFailure(Call<ShopStatusModel> call, Throwable t) {
                AppUtils.hideDialog();
            }
        });


    }

}