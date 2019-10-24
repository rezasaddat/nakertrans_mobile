package com.nakertrans.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nakertrans.R;

public class ForgotActivity extends AppCompatActivity {
    private EditText username;
    private Button btnSubmit, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        username = findViewById(R.id.forgotUsername);
        btnSubmit = findViewById(R.id.forgotButton);
        btnLogin = findViewById(R.id.loginPage);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(ForgotActivity.this, LoginActivity.class);
                ActivityOptions options = ActivityOptions.makeCustomAnimation(ForgotActivity.this,
                        android.R.anim.fade_in, android.R.anim.fade_out);
                startActivity(intent, options.toBundle());
                finish();
            }
        });
    }
}
