package com.nakertrans.master_data;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nakertrans.R;
import com.nakertrans.detail_content.SumberDataDetail;
import com.nakertrans.extras.SumberDataOptions;
import com.nakertrans.master_content.ContentSumberData;

import java.util.List;

public class SumberDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 0;
    public List<ContentSumberData.ItemList> itemList;
    final SumberDataMain.OnListFragmentInteractionListener mListener;

    public SumberDataAdapter (List<ContentSumberData.ItemList> list,
                              SumberDataMain.OnListFragmentInteractionListener listener){
        itemList = list;
        mListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType){
//        if (ViewType == VIEW_TYPE_ITEM){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sumber_data_item,
                    parent, false);
            return new ItemViewHolder(view);
//        }else{
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading,
//                    parent, false);
//            return new LoadingViewHolder(view);
//        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position){
//        if (viewHolder instanceof ItemViewHolder) {
            populateItemRows((ItemViewHolder) viewHolder, position);
//        } else if (viewHolder instanceof LoadingViewHolder) {
//            showLoadingView((LoadingViewHolder) viewHolder, position);
//        }
    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return itemList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public void clearItem(){
        int size = itemList.size();
        itemList.clear();
        notifyItemRangeRemoved(0, size);
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView mName;
        public final TextView mRows;
        public final TextView mCreated;
        public final CardView mCard;
        public final ImageButton mBtnOps;
        public ContentSumberData.ItemList mItem;

        public ItemViewHolder(@NonNull View view) {
            super(view);
            mView = view;
            mName = view.findViewById(R.id.sbTableName);
            mRows = view.findViewById(R.id.sbRowCount);
            mCard = view.findViewById(R.id.sbCard);
            mCreated = view.findViewById(R.id.sbCreated);
            mBtnOps = view.findViewById(R.id.sbBtnOpt);
        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    private void showLoadingView(LoadingViewHolder viewHolder, int position) {
        //ProgressBar would be displayed
    }

    private void populateItemRows(ItemViewHolder holder, final int position) {
        holder.mItem = itemList.get(position);
        holder.mName.setText(itemList.get(position).table_name);
        holder.mRows.setText(itemList.get(position).total_record);

        holder.mCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(@NonNull  View view) {
                Toast.makeText(view.getContext(), "Card Sumber Data "+itemList.get(position).table_name, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(view.getContext(), SumberDataDetail.class);
                intent.putExtra("idSD", itemList.get(position).id);
                intent.putExtra("nameSD", itemList.get(position).table_name);

                ActivityOptions options = ActivityOptions.makeCustomAnimation(view.getContext(),
                        android.R.anim.slide_in_left, android.R.anim.fade_out);
                view.getContext().startActivity(intent, options.toBundle());
            }
        });

        holder.mBtnOps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(@NonNull View view) {
                Toast.makeText(view.getContext(), "BtnOps "+itemList.get(position).table_name,
                        Toast.LENGTH_SHORT).show();

                Bundle bundle = new Bundle();
                bundle.putString("idSD", itemList.get(position).id);
                bundle.putString("tableSD", itemList.get(position).table_name);

                SumberDataOptions sumberDataOptions = new SumberDataOptions();
                sumberDataOptions.setArguments(bundle);
                sumberDataOptions.show(((AppCompatActivity)view.getContext()).getSupportFragmentManager(),
                        "SumberDataOptions");
            }
        });

    }
}
