package com.nakertrans.master_data;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.nakertrans.R;
import com.nakertrans.master_content.ContentSumberData;

import java.util.ArrayList;

public class SumberDataMain extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    RecyclerView recyclerView;
    SumberDataAdapter sumberDataAdapter;
    ContentSumberData contentSumberData;
    OnListFragmentInteractionListener mListener;
    SwipeRefreshLayout refreshLayout;
    ProgressBar progressBar;

    boolean isLoading = false;
    public SumberDataMain() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sumber_data_main, container, false);
        sumberDataAdapter = new SumberDataAdapter(ContentSumberData.ITEM_LISTS, mListener);
        recyclerView = view.findViewById(R.id.rvSumberDataMain);
        refreshLayout = view.findViewById(R.id.swipeSumberData);
        progressBar = view.findViewById(R.id.sbProgressbar);

        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeColors(
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimaryDark));

        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
                if (sumberDataAdapter.getItemCount() == 0) {
                    populateData();
                }else{
                    sumberDataAdapter.clearItem();
                    populateData();
                }
                initAdapter();
            }
        });

        initScrollListener();
        return view;
    }

    private void populateData() {
        contentSumberData.populateData(0, 10);
    }

    private void initAdapter() {
        recyclerView.setAdapter(sumberDataAdapter);
        refreshLayout.setRefreshing(false);
    }

    private void initScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading) {
                    if (linearLayoutManager != null &&
                            linearLayoutManager.findLastCompletelyVisibleItemPosition() ==
                                    sumberDataAdapter.getItemCount() - 1) {
                        //bottom of list!
                        loadMore();
                        progressBar.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    private void loadMore() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sumberDataAdapter.itemList.remove(sumberDataAdapter.getItemCount() - 1);
                int scrollPosition = sumberDataAdapter.getItemCount();
                sumberDataAdapter.notifyItemRemoved(scrollPosition);
                int currentSize = scrollPosition;
                int nextLimit = currentSize + 10;

                contentSumberData.populateData(currentSize, nextLimit);

                sumberDataAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.INVISIBLE);
            }
        }, 2000);


    }

    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(true);
        initAdapter();
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(ContentSumberData.ItemList item);
    }
}
