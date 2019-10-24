package com.nakertrans;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nakertrans.authentication.LoginActivity;
import com.nakertrans.processing.ChangePassword;

import java.util.Objects;

public class SettingActivity extends AppCompatActivity {
    public LinearLayout changePass, logoutApp;
    public ActivityOptions options;
    public Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        options = ActivityOptions.makeCustomAnimation(this,
                android.R.anim.slide_in_left, android.R.anim.fade_out);

        changePass = findViewById(R.id.changePassword);
        logoutApp = findViewById(R.id.settLogout);


        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getBaseContext(), ChangePassword.class);
                startActivity(intent, options.toBundle());
                finish();
            }
        });

        logoutApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                builder.setMessage("Anda ingin keluar dari aplikasi?");
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    intent = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(intent, options.toBundle());
                    finish();
                    }
                });

                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Tidak", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);
        return true;
    }
}
