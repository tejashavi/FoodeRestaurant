package com.foode.restaurant.view;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.foode.restaurant.common.OSettings;


public class BaseFragment extends Fragment {
    protected Activity mActivity;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mActivity = getActivity();
        new OSettings(mActivity);
    }
}
