package com.nakertrans.detail_content;

import android.app.ActivityOptions;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nakertrans.R;
import com.nakertrans.detail.PersonDetailSumberData;

import java.util.List;

public class SumberDataDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 0;
    public List<ContentDetailSumberData.DetailItem> detailItemList;
    final SumberDataDetail.OnListFragmentInteractionListener mListener;

    public SumberDataDetailAdapter(List<ContentDetailSumberData.DetailItem> list,
                              SumberDataDetail.OnListFragmentInteractionListener listener){
        detailItemList = list;
        mListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sumber_data_detail_item,
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
        public ContentDetailSumberData.DetailItem mItem;
        public final TextView listNIK, listNama, listTempat, listJenisKelamin;
        public final CardView cardDetail;

        public DetailItemViewHolder(@NonNull View view) {
            super(view);
            mView = view;
            listNIK = view.findViewById(R.id.detailNIK);
            listNama = view.findViewById(R.id.detailNama);
            listTempat = view.findViewById(R.id.detailTTL);
            listJenisKelamin = view.findViewById(R.id.detailJk);
            cardDetail = view.findViewById(R.id.cardSumberDataDetail);
        }
    }

    private void populateItemRows(DetailItemViewHolder holder, final int position) {
        holder.mItem = detailItemList.get(position);
        holder.listNIK.setText(detailItemList.get(position).nik);
        holder.listNama.setText(detailItemList.get(position).nama);
        holder.listTempat.setText(detailItemList.get(position).tempat+", "+
                detailItemList.get(position).tanggal_lahir);
        holder.listJenisKelamin.setText(detailItemList.get(position).jenis_kelamin);
        holder.cardDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Detail "+detailItemList.get(position).id
                        +" "+detailItemList.get(position).nama, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(view.getContext(), PersonDetailSumberData.class);
                intent.putExtra("sdNIK", detailItemList.get(position).nik);
                intent.putExtra("sdNama", detailItemList.get(position).nama);
                ActivityOptions options = ActivityOptions.makeCustomAnimation(view.getContext(),
                        android.R.anim.slide_in_left, android.R.anim.fade_out);
                view.getContext().startActivity(intent, options.toBundle());
            }
        });

    }

}
