package com.foode.restaurant.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foode.restaurant.R;
import com.foode.restaurant.adapter.PendingOrderAdapter;
import com.foode.restaurant.build.api.ApiClient;
import com.foode.restaurant.build.api.ApiInterface;
import com.foode.restaurant.common.AppSettings;
import com.foode.restaurant.helper.ConnectionHelper;
import com.foode.restaurant.interfaces.UpdateOrder;
import com.foode.restaurant.models.CommonModel;
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
public class PendingOrderFragment extends BaseFragment implements UpdateOrder {


    public PendingOrderFragment() {
        // Required empty public constructor
    }

    View view;
    RecyclerView rvList;
    TextView tvNoRecord;
    ConnectionHelper connectionHelper;
    ApiInterface apiInterface;
    UpdateOrder updateOrder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_pending_order, container, false);
        updateOrder = this;

        return view;
    }

    void findViewById(View view) {
        connectionHelper = new ConnectionHelper(mActivity);
        apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        rvList = view.findViewById(R.id.rvList);
        tvNoRecord = view.findViewById(R.id.tvNoRecord);

        if (connectionHelper.isConnectingToInternet()) {
            getPendingOrder();
        } else {
            AppUtils.showToastSort(mActivity, getString(R.string.errorInternet));
        }
    }

    @Override
    public void setUserVisibleHint(boolean visible) {
        super.setUserVisibleHint(visible);
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

    @Override
    public void onStop() {
        super.onStop();
    }

    void getPendingOrder() {
        // AppUtils.showRequestDialog(mActivity);
        HashMap<String, String> hm = new HashMap<>();
        hm.put("shop_id", AppSettings.getString(AppSettings.shopId));
        // hm.put("shop_id", "1");
        hm.put("value", "3");
        Call<PendingOrderModel> pendingOrderModelCall = apiInterface.getPendingOrder(hm);
        pendingOrderModelCall.enqueue(new Callback<PendingOrderModel>() {
            @Override
            public void onResponse(Call<PendingOrderModel> call, Response<PendingOrderModel> response) {
                AppUtils.hideDialog();
                PendingOrderModel pendingOrderModel = response.body();
                if (pendingOrderModel.getStatus().equalsIgnoreCase("SUCCESS")) {
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 1);
                    PendingOrderAdapter pendingOrderAdapter = new PendingOrderAdapter(mActivity, pendingOrderModel, updateOrder);
                    rvList.setLayoutManager(gridLayoutManager);
                    rvList.setAdapter(pendingOrderAdapter);
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

    void updateStatus(String orderId, String status) {
        // AppUtils.showRequestDialog(mActivity);
        HashMap<String, String> hm = new HashMap<>();
        hm.put("shop_id", AppSettings.getString(AppSettings.shopId));
        hm.put("order_id", orderId);
        hm.put("order_status", status);
        Call<CommonModel> commonModelCall = apiInterface.updateStatusOfIncomingOrder(hm);
        commonModelCall.enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {
                AppUtils.hideDialog();
                CommonModel commonModel = response.body();
                if (commonModel.getStatus().equalsIgnoreCase("SUCCESS")) {
                    AppUtils.showToastSort(mActivity, commonModel.getMessage());
                    getPendingOrder();
                } else {
                    AppUtils.showToastSort(mActivity, commonModel.getMessage());
                }
            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {
                AppUtils.hideDialog();
            }
        });

    }

    @Override
    public void UpdateStatus(int orderId, String status) {
        AppUtils.showRequestDialog(mActivity);
        HashMap<String, String> hm = new HashMap<>();
        hm.put("shop_id", AppSettings.getString(AppSettings.shopId));
        hm.put("order_id", String.valueOf(orderId));
        hm.put("order_status", status);
        Call<CommonModel> commonModelCall = apiInterface.updateStatusOfIncomingOrder(hm);
        commonModelCall.enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {
                AppUtils.hideDialog();
                CommonModel commonModel = response.body();
                if (commonModel.getStatus().equalsIgnoreCase("SUCCESS")) {
                    AppUtils.showToastSort(mActivity, commonModel.getMessage());
                    //    getPendingOrder();
                    if (status.equalsIgnoreCase("2")) {
                        getPendingOrder();
                    } else if (status.equals("1")) {
                        showReadyTimeDialog(String.valueOf(orderId));
                    }
                } else {
                    AppUtils.showToastSort(mActivity, commonModel.getMessage());
                }
            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {
                AppUtils.hideDialog();
            }
        });
    }

    void showReadyTimeDialog(String orderId) {
        final Dialog dialog = new Dialog(mActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_ready_time);
        Button btnOk = dialog.findViewById(R.id.btnOk);
        EditText etReadyTime = dialog.findViewById(R.id.etReadyTime);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etReadyTime.getText().toString().isEmpty()) {
                    AppUtils.showToastSort(mActivity, "Please enter ready time.");
                } else {
                    if (AppUtils.isNetworkAvailable(mActivity)) {
                        updateReadyTime(orderId, etReadyTime.getText().toString().trim());
                    } else {
                        AppUtils.showToastSort(mActivity, getString(R.string.errorInternet));
                    }

                }
            }
        });


        dialog.show();
    }

    void updateReadyTime(String orderId, String time) {
        AppUtils.showRequestDialog(mActivity);
        HashMap<String, String> hm = new HashMap<>();
        hm.put("shop_id", AppSettings.getString(AppSettings.shopId));
        hm.put("order_id", orderId);
        hm.put("order_ready_time", time);
        Call<CommonModel> commonModelCall = apiInterface.updateStatusOfIncomingOrder(hm);
        commonModelCall.enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {
                AppUtils.hideDialog();
                CommonModel commonModel = response.body();
                if (commonModel.getStatus().equalsIgnoreCase("SUCCESS")) {
                    AppUtils.showToastSort(mActivity, commonModel.getMessage());
                    AllOrderFragment.viewPager.setCurrentItem(1);
                } else {
                    AppUtils.showToastSort(mActivity, commonModel.getMessage());
                }
            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {
                AppUtils.hideDialog();
            }
        });
    }
}