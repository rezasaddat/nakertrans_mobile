package com.nakertrans.master_data;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nakertrans.R;
import com.nakertrans.detail_content.ProgramDetail;
import com.nakertrans.extras.ProgramOptions;
import com.nakertrans.master_content.ContentProgram;

import java.util.List;

public class ProgramAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 0;
    public List<ContentProgram.ItemList> programList;
    public ProgramMain.OnListFragmentInteractionListener programlistener;

    public ProgramAdapter(List<ContentProgram.ItemList> lists, ProgramMain.OnListFragmentInteractionListener listener){
        programList = lists;
        programlistener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.program_item,
                parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position){
        populateItemRows((ItemViewHolder) viewHolder, position);
    }

    @Override
    public int getItemCount() {
        return programList == null ? 0 : programList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return programList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public void clearItem(){
        int size = programList.size();
        programList.clear();
        notifyItemRangeRemoved(0, size);
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public String mid;
        public final TextView mContentView;
        public final TextView mSumber;
        public final TextView mPelaksanaan;
        public final TextView mPencapaian;
        public final CardView mCardView;
        public final ImageView mBtnOps;
        public ContentProgram.ItemList itemList;

        public ItemViewHolder(@NonNull View view) {
            super(view);
            mView = view;
            mid = "";
            mContentView = view.findViewById(R.id.contentProgram);
            mSumber = view.findViewById(R.id.sumberProgram);
            mPelaksanaan = view.findViewById(R.id.pelaksanaanProgram);
            mPencapaian = view.findViewById(R.id.pencapaianProgram);
            mCardView = view.findViewById(R.id.cardProgram);
            mBtnOps = view.findViewById(R.id.buttonOpsProgram);
        }
    }

    private void populateItemRows(ItemViewHolder holder, final int position) {
        holder.itemList = programList.get(position);
        holder.mid = programList.get(position).id;
        holder.mContentView.setText(programList.get(position).content);
        holder.mSumber.setText(programList.get(position).sumberdata);
        holder.mPelaksanaan.setText(programList.get(position).pelaksanaan);
        holder.mPencapaian.setText(programList.get(position).pencapaian);

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Card Program"+programList.get(position).content,
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(view.getContext(), ProgramDetail.class);
                intent.putExtra("idPrograms", programList.get(position).id);
                intent.putExtra("nPrograms", programList.get(position).content);

                ActivityOptions options = ActivityOptions.makeCustomAnimation(view.getContext(),
                        android.R.anim.slide_in_left, android.R.anim.fade_out);
                view.getContext().startActivity(intent, options.toBundle());
            }
        });

        holder.mBtnOps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Button Program"+programList.get(position).content,
                        Toast.LENGTH_SHORT).show();

                Bundle bundle = new Bundle();
                bundle.putString("idPrograms", programList.get(position).id);
                bundle.putString("namePrograms", programList.get(position).content);

                ProgramOptions programOptions = new ProgramOptions();
                programOptions.setArguments(bundle);
                programOptions.show(((AppCompatActivity)view.getContext()).getSupportFragmentManager(),
                        "programOptions");
            }
        });

    }
}
