package com.nakertrans.master_data;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nakertrans.R;
import com.nakertrans.detail.DetailBerita;
import com.nakertrans.extras.BeritaOptions;
import com.nakertrans.master_content.ContentBerita;

import java.util.List;

public class BeritaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 0;
    public List<ContentBerita.ItemList> beritaList;
    public BeritaMain.OnListFragmentInteractionListener beritaListener;

    public BeritaAdapter(List<ContentBerita.ItemList> lists, BeritaMain.OnListFragmentInteractionListener listener){
        beritaList = lists;
        beritaListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.berita_item,
                parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position){
        populateItemRows((ItemViewHolder) viewHolder, position);
    }

    @Override
    public int getItemCount() {
        return beritaList == null ? 0 : beritaList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return beritaList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public void clearItem(){
        int size = beritaList.size();
        beritaList.clear();
        notifyItemRangeRemoved(0, size);
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView mJudul;
        public final TextView mCreated;
        public final CardView mCardView;
        public final ImageButton mBtnOps;
        public final ImageView mImage;
        public ContentBerita.ItemList mItem;

        public ItemViewHolder(@NonNull View view) {
            super(view);
            mView = view;
            mJudul = view.findViewById(R.id.contentBerita);
            mCreated = view.findViewById(R.id.beritaCreated);
            mCardView = view.findViewById(R.id.cardBerita);
            mBtnOps = view.findViewById(R.id.buttonOpsBerita);
            mImage = view.findViewById(R.id.imageBerita);
        }
    }

    private void populateItemRows(ItemViewHolder holder, final int position) {
        holder.mItem = beritaList.get(position);
        holder.mJudul.setText(beritaList.get(position).beritaJudul);
        holder.mCreated.setText(beritaList.get(position).beritaCreated);

        if (Float.parseFloat(beritaList.get(position).id) % 2 ==0){
            holder.mImage.setImageResource(R.drawable.img1);
        }else{
            holder.mImage.setImageResource(R.drawable.img2);
        }

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Card Berita"+beritaList.get(position).beritaJudul,
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), DetailBerita.class);
                intent.putExtra("idBerita", beritaList.get(position).id);
                intent.putExtra("judulBerita", beritaList.get(position).beritaJudul);
                intent.putExtra("detailBerita", beritaList.get(position).beritaDetails);

                ActivityOptions options = ActivityOptions.makeCustomAnimation(view.getContext(),
                        android.R.anim.slide_in_left, android.R.anim.fade_out);
                view.getContext().startActivity(intent, options.toBundle());
            }
        });

        holder.mBtnOps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Button Berita"+beritaList.get(position).beritaJudul,
                        Toast.LENGTH_SHORT).show();

                Bundle bundle = new Bundle();
                bundle.putString("idBerita", beritaList.get(position).id);
                bundle.putString("nameBerita", beritaList.get(position).beritaJudul);

                BeritaOptions beritaOptions = new BeritaOptions();
                beritaOptions.setArguments(bundle);
                beritaOptions.show(((AppCompatActivity)view.getContext()).getSupportFragmentManager(),
                        "beritaOptions");
            }
        });

    }
}
