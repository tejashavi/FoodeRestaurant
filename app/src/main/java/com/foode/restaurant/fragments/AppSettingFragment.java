package com.foode.restaurant.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.foode.restaurant.BuildConfig;
import com.foode.restaurant.R;
import com.foode.restaurant.activities.LoginActivity;
import com.foode.restaurant.activities.MainActivity2;
import com.foode.restaurant.activities.RestaurantDetailActivity;
import com.foode.restaurant.common.AppSettings;
import com.foode.restaurant.view.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class AppSettingFragment extends BaseFragment {


    public AppSettingFragment() {
        // Required empty public constructor
    }

    RelativeLayout rlRestaurantDetail, rlLogout;
    TextView tvVersion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_app_setting, container, false);
        MainActivity2.switchOnOff.setVisibility(View.GONE);
        findViewById(view);
        return view;
    }

    void findViewById(View view) {
        rlRestaurantDetail = view.findViewById(R.id.rlRestaurantDetail);
        rlLogout = view.findViewById(R.id.rlLogout);
        tvVersion = view.findViewById(R.id.tvVersion);

        rlLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppSettings.clearSharedPreference();
                startActivity(new Intent(mActivity, LoginActivity.class));
            }
        });
        tvVersion.setText(BuildConfig.VERSION_NAME + "");

        rlRestaurantDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, RestaurantDetailActivity.class));
            }
        });
    }
}