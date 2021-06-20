package com.foode.restaurant.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foode.restaurant.R;
import com.foode.restaurant.adapter.AcceptedOrderAdapter;
import com.foode.restaurant.build.api.ApiClient;
import com.foode.restaurant.build.api.ApiInterface;
import com.foode.restaurant.common.AppSettings;
import com.foode.restaurant.helper.ConnectionHelper;
import com.foode.restaurant.models.PendingOrderModel;
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
public class AcceptedOrderFragment extends BaseFragment {


    public AcceptedOrderFragment() {
        // Required empty public constructor
    }
    View view;
    RecyclerView rvList;
    TextView tvNoRecord;
    ConnectionHelper connectionHelper;
    ApiInterface apiInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_accepted_order, container, false);
        return view;
    }

    void findViewById(View view) {
        connectionHelper = new ConnectionHelper(mActivity);
        apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        rvList = view.findViewById(R.id.rvList);
        tvNoRecord = view.findViewById(R.id.tvNoRecord);

        if (connectionHelper.isConnectingToInternet()) {
            getAcceptedOrder();
        } else {
            AppUtils.showToastSort(mActivity, getString(R.string.errorInternet));
        }
    }
    @Override
    public void setUserVisibleHint(boolean visible) {
        super.setUserVisibleHint( visible );
        if (visible && isResumed()) {
            //Only manually call onResume if fragment is already visible
            //Otherwise allow natural fragment lifecycle to call onResume
            onResume();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        if (!getUserVisibleHint()) {

            return;
        }

        findViewById(view);

    }
    void getAcceptedOrder() {
        //AppUtils.showRequestDialog(mActivity);
        HashMap<String, String> hm = new HashMap<>();
         hm.put("shop_id", AppSettings.getString(AppSettings.shopId));
        //hm.put("shop_id", "3");
        hm.put("value", "3");
        Call<PendingOrderModel> pendingOrderModelCall = apiInterface.getAcceptedOrder(hm);
        pendingOrderModelCall.enqueue(new Callback<PendingOrderModel>() {
            @Override
            public void onResponse(Call<PendingOrderModel> call, Response<PendingOrderModel> response) {
                AppUtils.hideDialog();
                PendingOrderModel pendingOrderModel = response.body();
                if (pendingOrderModel.getStatus().equalsIgnoreCase("SUCCESS")) {
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 1);
                    AcceptedOrderAdapter acceptedOrderAdapter = new AcceptedOrderAdapter(mActivity, pendingOrderModel);
                    rvList.setLayoutManager(gridLayoutManager);
                    rvList.setAdapter(acceptedOrderAdapter);
                    tvNoRecord.setVisibility(View.GONE);
                    rvList.setVisibility(View.VISIBLE);
                } else {
                    AppUtils.showToastSort(mActivity, pendingOrderModel.getMessage());
                    tvNoRecord.setVisibility(View.VISIBLE);
                    rvList.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<PendingOrderModel> call, Throwable t) {
                AppUtils.hideDialog();
            }
        });


    }

}