package com.foode.restaurant.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.foode.restaurant.R;
import com.foode.restaurant.adapter.IncomingOrder;
import com.foode.restaurant.helper.ConnectionHelper;
import com.foode.restaurant.utils.AppUtils;
import com.foode.restaurant.view.BaseFragment;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);
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

                AppUtils.showToastSort(mActivity, "Show");
            }
        });

    }
   /* public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        System.out.println("HomeFragment");
        connectionHelper = new ConnectionHelper(mActivity);
        toolbar = (ViewGroup) getActivity().findViewById(R.id.toolbar);
        toolbar.setVisibility(View.VISIBLE);
        toolbarLayout = LayoutInflater.from(mActivity).inflate(R.layout.toolbar_home, toolbar, false);
        toolbar.addView(toolbarLayout);

    }*/
}
