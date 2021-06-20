package com.foode.restaurant.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.foode.restaurant.R;
import com.foode.restaurant.build.api.ApiClient;
import com.foode.restaurant.build.api.ApiInterface;
import com.foode.restaurant.common.AppSettings;
import com.foode.restaurant.helper.ConnectionHelper;
import com.foode.restaurant.models.Login;
import com.foode.restaurant.utils.AppUtils;
import com.foode.restaurant.view.BaseActivity;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends BaseActivity implements View.OnClickListener {

    EditText etEmailAddress, etPassword;
    ImageView ivPassword;
    Button btnSignIn;
    TextView tvForgotPassword;
    ApiInterface apiInterface;
    ConnectionHelper connectionHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        connectionHelper = new ConnectionHelper(mActivity);
        findViewById();

    }

    void findViewById() {
        etEmailAddress = findViewById(R.id.etEmailAddress);
        etPassword = findViewById(R.id.etPassword);
        ivPassword = findViewById(R.id.ivPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);

        ivPassword.setTag(1);
        setOnClickListener();
    }

    void setOnClickListener() {
        ivPassword.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivPassword:
                if (ivPassword.getTag().equals(1)) {
                    etPassword.setTransformationMethod(null);
                    ivPassword.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.ic_eye_close));
                    ivPassword.setTag(0);
                } else {
                    ivPassword.setTag(1);
                    etPassword.setTransformationMethod(new PasswordTransformationMethod());
                    ivPassword.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.ic_eye_open));
                }
                return;

            case R.id.btnSignIn:
               /* startActivity(new Intent(mActivity, MainActivity2.class));
                finish();*/
                if (etEmailAddress.getText().toString().isEmpty()) {
                    AppUtils.showToastSort(mActivity, "Please enter email address");
                } else if (etPassword.getText().toString().isEmpty()) {
                    AppUtils.showToastSort(mActivity, "Please enter password");
                } else if (connectionHelper.isConnectingToInternet()) {
                    login();
                } else {
                    AppUtils.showToastSort(mActivity, getString(R.string.errorInternet));
                }

                return;
        }
    }

    void login() {
        AppUtils.hideSoftKeyboard(mActivity);
        AppUtils.showRequestDialog(mActivity);
        HashMap<String, String> hm = new HashMap<>();
        hm.put("emailid", etEmailAddress.getText().toString().trim());
        hm.put("password", etPassword.getText().toString().trim());
        Call<Login> loginCall = apiInterface.login(hm);
        loginCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                AppUtils.hideDialog();
                Login login = response.body();
                if (login.getStatus().equalsIgnoreCase("SUCCESS")) {
                    AppSettings.putString(AppSettings.shopId, String.valueOf(login.getData().get(0).getId()));
                    startActivity(new Intent(mActivity, MainActivity2.class));
                    finish();
                } else {
                    AppUtils.showToastSort(mActivity, login.getMessage());
                }

            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                AppUtils.hideDialog();
            }
        });
    }
}