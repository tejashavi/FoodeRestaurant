package com.foode.restaurant.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foode.restaurant.R;
import com.foode.restaurant.activities.MainActivity2;
import com.foode.restaurant.adapter.AllOrderAdapter;
import com.foode.restaurant.build.api.ApiClient;
import com.foode.restaurant.build.api.ApiInterface;
import com.foode.restaurant.common.AppSettings;
import com.foode.restaurant.helper.ConnectionHelper;
import com.foode.restaurant.models.PendingOrderModel;
import com.foode.restaurant.utils.AppUtils;
import com.foode.restaurant.view.BaseFragment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class SaleFragment extends BaseFragment {


    public SaleFragment() {
        // Required empty public constructor
    }

    //Int
    int mYear, mMonth, mDay;

    //String
    String fromDate = "", toDate = "";
    RecyclerView rvList;
    TextView tvNoRecord;
    ConnectionHelper connectionHelper;
    ApiInterface apiInterface;
    TextView tvTotalOrder, tvTotalAmount;
    EditText etFromDate, etToDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_sale, container, false);
        MainActivity2.switchOnOff.setVisibility(View.GONE);
        findViewById(view);
        return view;
    }

    void findViewById(View view) {
        connectionHelper = new ConnectionHelper(mActivity);
        apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        rvList = view.findViewById(R.id.rvList);
        tvNoRecord = view.findViewById(R.id.tvNoRecord);
        tvTotalOrder = view.findViewById(R.id.tvTotalOrder);
        tvTotalAmount = view.findViewById(R.id.tvTotalAmount);
        etFromDate = view.findViewById(R.id.etFromDate);
        etToDate = view.findViewById(R.id.etToDate);

        if (connectionHelper.isConnectingToInternet()) {
            getPreviousOrder();
        } else {
            AppUtils.showToastSort(mActivity, getString(R.string.errorInternet));
        }

        etFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalenderDialog(etFromDate, "1");

                //   datePicker(edt_from_date);
            }
        });
        etToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etFromDate.getText().toString().trim().equals(""))
                    openCalenderDialog(etToDate, "2");
                else
                    Toast.makeText(mActivity, "Please select from date first", Toast.LENGTH_SHORT).show();
                // AppUtils.showToastSort(mActivity, "Please select from date first");
                // datePicker(edt_to_date);
            }
        });
    }

    void getPreviousOrder() {

        AppUtils.showRequestDialog(mActivity);
        HashMap<String, String> hm = new HashMap<>();
        hm.put("shop_id", AppSettings.getString(AppSettings.shopId));
        //  hm.put("shop_id", "3");
        hm.put("value", "3");
        Call<PendingOrderModel> pendingOrderModelCall = apiInterface.getPrevousOrderList(hm);
        pendingOrderModelCall.enqueue(new Callback<PendingOrderModel>() {
            @Override
            public void onResponse(Call<PendingOrderModel> call, Response<PendingOrderModel> response) {
                AppUtils.hideDialog();
                PendingOrderModel pendingOrderModel = response.body();
                if (pendingOrderModel.getStatus().equalsIgnoreCase("SUCCESS")) {
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 1);
                    AllOrderAdapter allOrderAdapter = new AllOrderAdapter(mActivity, pendingOrderModel);
                    rvList.setLayoutManager(gridLayoutManager);
                    rvList.setAdapter(allOrderAdapter);
                    tvNoRecord.setVisibility(View.GONE);
                    rvList.setVisibility(View.VISIBLE);
                    tvTotalOrder.setText(pendingOrderModel.getData().size() + "");
                    int totalAmount = 0;
                    for (int i = 0; i < pendingOrderModel.getData().size(); i++) {
                        totalAmount += pendingOrderModel.getData().get(i).getUserInfo().getPayableAmount();
                    }
                    tvTotalAmount.setText("Rs." + totalAmount);
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

    private void openCalenderDialog(final EditText editText, final String type) {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        if (type.equals("1")) {
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            Log.v("fwqef", mYear + " " + mMonth + " " + mDay);
        } else {
            mYear = Integer.parseInt(getYearFromDate(etFromDate.getText().toString().trim()));
            mMonth = Integer.parseInt(getMonthFromDate(etFromDate.getText().toString().trim())) - 1;
            mDay = Integer.parseInt(getDayFromDate(etFromDate.getText().toString().trim()));
            Log.v("fwqef", mYear + " " + mMonth + " " + mDay);
        }

        DatePickerDialog datePickerDialog = new DatePickerDialog(mActivity,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        String month = "";

                        if (type.equals("1"))
                            month = String.valueOf(monthOfYear + 1);
                        else
                            month = String.valueOf(monthOfYear + 1);

                        String day = String.valueOf(dayOfMonth);

                        if (monthOfYear + 1 < 10)
                            month = "0" + month;

                        if (dayOfMonth < 10)
                            day = "0" + day;

                        if (type.equals("1")) {

                            fromDate = year + "-" + month + "-" + day;
                            toDate = "";
                            editText.setText(changeDateFormat(year + "-" + month + "-" + day));
                            etToDate.setText("");
                        } else {
                            toDate = year + "-" + month + "-" + day;
                            editText.setText(changeDateFormat(year + "-" + month + "-" + day));


                          //  getPreviousOrder();
                        }
                    }
                },
                mYear, mMonth, mDay);

        if (type.equals("1")) {

            //c.add(Calendar.DAY_OF_MONTH, -15);
            // datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
            // c.add(Calendar.DAY_OF_MONTH, +15);
            // c.add(Calendar.DAY_OF_MONTH, +60);

            // datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
            datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());

        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM, yyyy");
            Calendar currentCal = Calendar.getInstance();
            String currentdate = dateFormat.format(currentCal.getTime());
            if (etFromDate.getText().toString().trim().equals(currentdate)) {
                datePickerDialog.getDatePicker().setMinDate(new Date().getTime());
                datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
            } else {
                datePickerDialog.getDatePicker().setMinDate(getDateToTimeStamp(etFromDate.getText().toString().trim()));
                datePickerDialog.getDatePicker().setMaxDate(getDateToTimeStamp(etFromDate.getText().toString().trim()) + TimeUnit.DAYS.toMillis(30));
            }


        }

        datePickerDialog.show();
    }

    private String changeDateFormat(String prev_date) {

        DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        DateFormat targetFormat = new SimpleDateFormat("dd MMM, yyyy");
        Date date = null;
        try {
            date = originalFormat.parse(prev_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = targetFormat.format(date);

        return formattedDate;
    }


    private String getDayFromDate(String prev_date) {


        DateFormat originalFormat = new SimpleDateFormat("dd MMM, yyyy", Locale.ENGLISH);
        DateFormat targetFormat = new SimpleDateFormat("dd");
        Date date = null;
        try {
            date = originalFormat.parse(prev_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = targetFormat.format(date);

        return formattedDate;
    }

    private String getMonthFromDate(String prev_date) {


        DateFormat originalFormat = new SimpleDateFormat("dd MMM, yyyy", Locale.ENGLISH);
        DateFormat targetFormat = new SimpleDateFormat("MM");
        Date date = null;
        try {
            date = originalFormat.parse(prev_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = targetFormat.format(date);

        return formattedDate;
    }

    private String getYearFromDate(String prev_date) {


        DateFormat originalFormat = new SimpleDateFormat("dd MMM, yyyy", Locale.ENGLISH);
        DateFormat targetFormat = new SimpleDateFormat("yyyy");
        Date date = null;
        try {
            date = originalFormat.parse(prev_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = targetFormat.format(date);

        return formattedDate;
    }

    private long getDateToTimeStamp(String given_date) {

        DateFormat formatter = new SimpleDateFormat("dd MMM, yyyy");
        Date date = null;
        try {
            date = (Date) formatter.parse(given_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long timestamp = date.getTime();

        return timestamp;

    }
}