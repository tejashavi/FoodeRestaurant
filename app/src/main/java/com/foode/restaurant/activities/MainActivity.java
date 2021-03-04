package com.foode.restaurant.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.foode.restaurant.fragment.InventoryFragment;
import com.foode.restaurant.fragment.MoreFragment;
import com.foode.restaurant.fragment.OrderFragment;
import com.foode.restaurant.fragment.ReportFragment;
import com.foode.restaurant.R;
import com.foode.restaurant.build.api.ApiClient;
import com.foode.restaurant.build.api.ApiInterface;
import com.foode.restaurant.helper.ConnectionHelper;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final int TIME_DELAY = 2000;
    // LogCat tag
    private static final String TAG = "HomeActivity";
    private static final int ASK_MULTIPLE_PERMISSION_REQUEST_CODE = 0;
    public static AHBottomNavigation bottomNavigation;

    private static long back_pressed;

    ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
    Context context = MainActivity.this;

    Retrofit retrofit;
    FragmentTransaction transaction;
    private ConnectionHelper connectionHelper;
    private Fragment fragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectionHelper = new ConnectionHelper(this);

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

        // Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Orders", R.drawable.ic_baseline_order, R.color.grey);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Inventory", R.drawable.ic_baseline_inventroy, R.color.grey);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Reports", R.drawable.ic_baseline_report, R.color.grey);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("More", R.drawable.ic_baseline_more_horiz, R.color.grey);
        // Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);

        // Set background color
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));


        // Disable the translation inside the CoordinatorLayout
        bottomNavigation.setBehaviorTranslationEnabled(true);
        bottomNavigation.setTranslucentNavigationEnabled(true);

        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

// Change colors
        bottomNavigation.setAccentColor(Color.parseColor("#FF5722"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));

        fragment = new OrderFragment();
        transaction.add(R.id.main_container, fragment).commit();
        bottomNavigation.setCurrentItem(0);

        // Set listeners
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                // Do something cool here...
                switch (position) {
                    case 0:
                        fragment = new OrderFragment();
                        break;
                    case 1:
                        fragment = new InventoryFragment();
                        break;
                    case 2:
                        fragment = new ReportFragment();
                        break;
                    case 3:
                        fragment = new MoreFragment();
                        break;
                }

                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_container, fragment).commit();
                return true;
            }
        });

    }
}