package com.nakertrans.detail_content;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nakertrans.R;

import java.util.List;

public class ProgramDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 0;
    public List<ContentDetailProgram.ProgramDetailItem> detailItemList;
    final ProgramDetail.OnListFragmentInteractionListener mListener;

    public ProgramDetailAdapter(List<ContentDetailProgram.ProgramDetailItem> list,
                                   ProgramDetail.OnListFragmentInteractionListener listener){
        detailItemList = list;
        mListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.program_detail_item,
                parent, false);
        return new DetailItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position){
        populateItemRows((DetailItemViewHolder) viewHolder, position);
    }

    @Override
    public int getItemCount() {
        return detailItemList == null ? 0 : detailItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return detailItemList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public void clearItem(){
        int size = detailItemList.size();
        detailItemList.clear();
        notifyItemRangeRemoved(0, size);
    }

    private class DetailItemViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public ContentDetailProgram.ProgramDetailItem mItem;
        public final TextView detailNIK, detailNama, detailTTL, detailJK, detailTercapai;
        public final CardView cardDetailProgram;

        public DetailItemViewHolder(@NonNull View view) {
            super(view);
            mView = view;
            detailNIK = view.findViewById(R.id.programnik);
            detailNama = view.findViewById(R.id.programnama);
            detailTTL = view.findViewById(R.id.programttl);
            detailJK = view.findViewById(R.id.programjk);
            detailTercapai = view.findViewById(R.id.programtercapai);
            cardDetailProgram = view.findViewById(R.id.cardDetailPrograms);
        }
    }

    private void populateItemRows(DetailItemViewHolder holder, final int position) {
        holder.mItem = detailItemList.get(position);
        holder.detailNIK.setText(detailItemList.get(position).prognik);
        holder.detailNama.setText(detailItemList.get(position).prognama);
        holder.detailTTL.setText(detailItemList.get(position).progtempat+", "+
                detailItemList.get(position).progtanggal_lahir);
        holder.detailJK.setText(detailItemList.get(position).progjenis_kelamin);
        holder.detailTercapai.setText(detailItemList.get(position).progtercapai);

        holder.cardDetailProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Detail "+detailItemList.get(position).progid
                        +" "+detailItemList.get(position).prognama, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
