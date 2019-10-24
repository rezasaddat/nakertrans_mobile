package com.nakertrans.authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nakertrans.MainActivity;
import com.nakertrans.R;
import com.nakertrans.global.Const;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    public EditText loginUser;
    public EditText loginPass;
    public AppCompatButton btnLogin, btnForgot;
    public ProgressBar loginProgress;
    public AnimationDrawable animationDrawable;
    public Const aConst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        loginUser = findViewById(R.id.loginUsername);
        loginPass = findViewById(R.id.loginPassword);
        btnLogin = findViewById(R.id.loginButton);
        btnForgot = findViewById(R.id.loginForgot);
        loginProgress = findViewById(R.id.loginProgressBar);

        loginPass.setTransformationMethod(new PasswordTransformationMethod());
        ConstraintLayout constraintLayout = findViewById(R.id.layoutLogin);
        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(0);
        animationDrawable.setExitFadeDuration(500);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, ForgotActivity.class);
                ActivityOptions options = ActivityOptions.makeCustomAnimation(LoginActivity.this,
                        android.R.anim.fade_in, android.R.anim.fade_out);
                startActivity(intent, options.toBundle());
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (animationDrawable != null && !animationDrawable.isRunning())
            animationDrawable.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (animationDrawable != null && animationDrawable.isRunning())
            animationDrawable.stop();
    }

    @Override
    public void onBackPressed(){
        finish();
        moveTaskToBack(true);
    }

    public void login(){
        if (!validate()){
            onLoginFailed();
            return;
        }

        btnLogin.setEnabled(false);
        loginProgress.setVisibility(View.VISIBLE);

        String username = loginUser.getText().toString();
        String password = loginPass.getText().toString();

        sendRequest();

//        if (username.equals("testing") && password.equals("12345")){
//            Toast.makeText(getBaseContext(), "Login Success", Toast.LENGTH_LONG).show();
//            Intent intent = new Intent();
//            intent.setClass(this, MainActivity.class);
//            ActivityOptions options = ActivityOptions.makeCustomAnimation(this,
//                    android.R.anim.fade_in, android.R.anim.fade_out);
//            startActivity(intent, options.toBundle());
//        }else{
//            Toast.makeText(getBaseContext(), "Login Failed", Toast.LENGTH_LONG).show();
//            Intent intent = new Intent();
//            intent.setClass(this, LoginActivity.class);
//            ActivityOptions options = ActivityOptions.makeCustomAnimation(this,
//                    android.R.anim.fade_in, android.R.anim.fade_out);
//            startActivity(intent, options.toBundle());
//        }


        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        onLoginSuccess();
                        loginProgress.setVisibility(View.INVISIBLE);
                    }
                }, 3000
        );
    }

    public void sendRequest(){
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://192.168.100.12/nakertrans_api/public/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Success", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.toString());
            }
        });
        queue.add(stringRequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    public void onLoginSuccess() {
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        btnLogin.setEnabled(true);
    }

    public boolean validate(){
        boolean valid = true;

        String username = loginUser.getText().toString();
        String password = loginPass.getText().toString();

        if (username.isEmpty()) {
            loginUser.setError("enter a valid username");
            valid = false;
        } else {
            loginUser.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            loginPass.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            loginPass.setError(null);
        }
        return valid;
    }
}
