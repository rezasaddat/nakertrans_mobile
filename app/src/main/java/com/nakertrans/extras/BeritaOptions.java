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

public class BeritaOptions extends BottomSheetDialogFragment {
    private TextView opsBeritaName;
    private LinearLayout opsShare;
    private LinearLayout opsDelete;

    public BeritaOptions(){}

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.berita_options, viewGroup, false);
        // getting value listview

        String idBerita = getArguments().getString("idBerita");
        final String nameBerita = getArguments().getString("nameBerita");

        opsBeritaName = view.findViewById(R.id.BeritaName);
        opsBeritaName.setText(idBerita + '/' + nameBerita);

        opsShare = view.findViewById(R.id.BeritaShare);
        opsShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(@NonNull View mview) {
                Toast.makeText(mview.getContext(), "Share "+nameBerita, Toast.LENGTH_SHORT).show();
            }
        });

        opsDelete = view.findViewById(R.id.BeritaDelete);
        opsDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(@NonNull View mview) {
                Toast.makeText(mview.getContext(), "Delete "+nameBerita, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
