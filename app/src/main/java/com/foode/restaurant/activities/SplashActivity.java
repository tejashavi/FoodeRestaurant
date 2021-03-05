package com.foode.restaurant.activities;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import com.foode.restaurant.R;
import com.foode.restaurant.common.AppSettings;
import com.foode.restaurant.view.BaseActivity;
import com.google.android.material.snackbar.Snackbar;

public class SplashActivity extends BaseActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context = this;

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 3000ms
                if (AppSettings.getString(AppSettings.shopId).isEmpty()) {
                    startActivity(new Intent(mActivity, LoginActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(mActivity, MainActivity.class));
                    finish();
                }

            }
        }, 3000);
    }

    public void displayMessage(String toastString) {
        try {
            Snackbar.make(getCurrentFocus(), toastString, Snackbar.LENGTH_SHORT).setAction("Action", null).show();
        } catch (Exception e) {
            try {
                Toast.makeText(context, "" + toastString, Toast.LENGTH_SHORT).show();
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
    }

}