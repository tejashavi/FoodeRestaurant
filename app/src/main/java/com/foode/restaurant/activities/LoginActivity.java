package com.foode.restaurant.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.foode.restaurant.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    Context context;

    @BindView(R.id.eye_img)
    ImageView eyeImg;
    @BindView(R.id.ed_password)
    EditText edPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        context = this;
        eyeImg.setTag(1);
    }


    @OnClick({R.id.eye_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.eye_img:
                if (eyeImg.getTag().equals(1)) {
                    edPassword.setTransformationMethod(null);
                    eyeImg.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_eye_close));
                    eyeImg.setTag(0);
                } else {
                    eyeImg.setTag(1);
                    edPassword.setTransformationMethod(new PasswordTransformationMethod());
                    eyeImg.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_eye_open));
                }
                break;

        }

    }
}