package com.nakertrans.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nakertrans.R;

public class DetailProgramsPerson extends AppCompatActivity {
    public LinearLayout progLayout;
    public TextView NIK, Nama;
    public Intent data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_programs_person);

        progLayout = findViewById(R.id.progLayoutDetail);
        NIK = findViewById(R.id.progNIK);
        Nama = findViewById(R.id.progNama);

        data = getIntent();
        setTitle(data.getStringExtra("progNama") + " | " + data.getStringExtra("progNik"));
        NIK.setText(data.getStringExtra("progNik"));
        Nama.setText(data.getStringExtra("progNama"));

        LinearLayout newLayout = new LinearLayout(this);
        newLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT ));
        newLayout.setOrientation(LinearLayout.HORIZONTAL);
        newLayout.setPadding(0,0,0,5);
        newLayout.setWeightSum(1);

        TextView newTextTitle = new TextView(this);
        newTextTitle.setLayoutParams(new LinearLayout.LayoutParams(340,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        newTextTitle.setText(R.string.title_tercapai);

        TextView newSemicolon = new TextView(this);
        newSemicolon.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        newSemicolon.setText(":");

        LinearLayout.LayoutParams styleOps = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 1);
        styleOps.setMarginStart(30);
        TextView newValue = new TextView(this);
        newValue.setLayoutParams(styleOps);
        newValue.setText(data.getStringExtra("progTercapai"));

        newLayout.addView(newTextTitle);
        newLayout.addView(newSemicolon);
        newLayout.addView(newValue);

        progLayout.addView(newLayout, 11);

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
