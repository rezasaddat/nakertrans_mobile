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

public class SumberDataOptions extends BottomSheetDialogFragment {
    private TextView opsTableName;
    private LinearLayout opsShare;
    private LinearLayout opsDownload;
    private LinearLayout opsDelete;
    public SumberDataOptions(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.sumberdata_options, viewGroup, false);
        // getting value listview

        String idSD = getArguments().getString("idSD");
        final String tableSD = getArguments().getString("tableSD");

        opsTableName = view.findViewById(R.id.SDTableName);
        opsTableName.setText(idSD + '/' + tableSD);

        opsShare = view.findViewById(R.id.SDShare);
        opsShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(@NonNull View mview) {
                Toast.makeText(mview.getContext(), "Share "+tableSD, Toast.LENGTH_SHORT).show();
            }
        });

        opsDownload = view.findViewById(R.id.SDDownload);
        opsDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(@NonNull View mview) {
                Toast.makeText(mview.getContext(), "Download "+tableSD, Toast.LENGTH_SHORT).show();
            }
        });

        opsDelete = view.findViewById(R.id.SDDelete);
        opsDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(@NonNull View mview) {
                Toast.makeText(mview.getContext(), "Delete "+tableSD, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
