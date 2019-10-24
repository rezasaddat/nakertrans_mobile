package com.nakertrans.extras;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.nakertrans.R;
import com.nakertrans.processing.BeritaForm;
import com.nakertrans.processing.ProgramsForm;
import com.nakertrans.processing.SumberDataSingle;

public class FabOptions extends BottomSheetDialogFragment
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    public FabOptions(){}

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fab_options, viewGroup, false);

        BottomNavigationView navigationView = view.findViewById(R.id.customBottomSheet);
        navigationView.setOnNavigationItemSelectedListener(this);

        BottomNavigationView navigationView1 = view.findViewById(R.id.customBottomSheet2);
        navigationView1.setOnNavigationItemSelectedListener(this);
        return view;
    }

    public boolean onNavigationItemSelected(MenuItem menuItem){
        Intent intent = new Intent();
        ActivityOptions options = ActivityOptions.makeCustomAnimation(getContext(),
                android.R.anim.slide_in_left, android.R.anim.fade_out);
        switch (menuItem.getItemId()){
            case R.id.add_file_sumber_data :
                Log.d("D", "onNavigationItemSelected: upload file");
                return true;
            case R.id.add_sumber_data :
                intent.setClass(getContext(), SumberDataSingle.class);
                startActivity(intent, options.toBundle());
                return true;
            case R.id.add_program :
                intent.setClass(getContext(), ProgramsForm.class);
                startActivity(intent, options.toBundle());
                return true;
            case R.id.add_berita :
                intent.setClass(getContext(), BeritaForm.class);
                startActivity(intent, options.toBundle());
                return true;
        }
        return false;
    }
}
