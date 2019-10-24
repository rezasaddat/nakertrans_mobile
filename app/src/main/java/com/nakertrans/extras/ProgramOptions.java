package com.nakertrans.extras;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.nakertrans.R;

public class ProgramOptions extends BottomSheetDialogFragment {
    private TextView opsProgramsName;
    private LinearLayout opsDelete;

    public ProgramOptions(){ }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.programs_options, viewGroup, false);
        // getting value listview

        final String idPrograms = getArguments().getString("idPrograms");
        final String namePrograms = getArguments().getString("namePrograms");

        opsProgramsName = view.findViewById(R.id.programName);
        opsProgramsName.setText(idPrograms+"/"+namePrograms);

        opsDelete = view.findViewById(R.id.ProgramDelete);
        opsDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(@NonNull View mview) {
                Toast.makeText(mview.getContext(), "Delete "+namePrograms, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
