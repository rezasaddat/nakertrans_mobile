package com.nakertrans.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.nakertrans.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DetailBerita extends AppCompatActivity {
    public String idBerita;
    public TextView dJudul;
    public ImageView dImage;
    public TextView dUpload;
    public TextView dTanggal;
    public TextView dDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_berita);

        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

        Intent i = getIntent();
        idBerita = i.getStringExtra("idBerita");
        dJudul = findViewById(R.id.detailJudulBerita);
        dImage = findViewById(R.id.imageDetailBerita);
        dUpload = findViewById(R.id.detailUploadBy);
        dTanggal = findViewById(R.id.detailTglBerita);
        dDetail = findViewById(R.id.detailContent);

        // set content
        dJudul.setText(i.getStringExtra("judulBerita"));
        if (Integer.parseInt(idBerita) % 2 == 0 ){
            dImage.setImageResource(R.drawable.img1);
        }else{
            dImage.setImageResource(R.drawable.img2);
        }
        dUpload.setText("admin nakertrans");
        dTanggal.setText(df.format(date));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            dDetail.setText(Html.fromHtml(i.getStringExtra("detailBerita"), Html.FROM_HTML_MODE_COMPACT));
        }else{
            dDetail.setText(Html.fromHtml(i.getStringExtra("detailBerita")));
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);
        return true;
    }
}
