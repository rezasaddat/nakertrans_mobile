package com.nakertrans;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import com.nakertrans.authentication.LoginActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent();
        intent.setClass(this, LoginActivity.class);
        ActivityOptions options = ActivityOptions.makeCustomAnimation(this,
                android.R.anim.fade_in, android.R.anim.fade_out);
        startActivity(intent, options.toBundle());
        finish();
    }
}
