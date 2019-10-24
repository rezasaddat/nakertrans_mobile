package com.nakertrans.detail_content;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.nakertrans.R;

public class SumberDataDetail extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView recyclerView;
    SumberDataDetailAdapter sumberDataDetailAdapter;
    ContentDetailSumberData contentDetailSumberData;
    OnListFragmentInteractionListener mListener;
    SwipeRefreshLayout refreshLayout;
    ProgressBar progressBar;
    boolean isLoading = false;

    public SumberDataDetail(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sumber_data_detail);
        Intent intent = getIntent();
        String sumberData = intent.getStringExtra("nameSD");
        setTitle(sumberData);

        sumberDataDetailAdapter = new SumberDataDetailAdapter(ContentDetailSumberData.DETAIL_ITEM_LIST, mListener);
        recyclerView = findViewById(R.id.rvSumberDataDetail);
        refreshLayout = findViewById(R.id.swipeSumberDataDetail);
        progressBar = findViewById(R.id.sbdProgressBar);

        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeColors(
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimaryDark));

        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
                if (sumberDataDetailAdapter.getItemCount() == 0) {
                    populateData();
                }else{
                    sumberDataDetailAdapter.clearItem();
                    populateData();
                }
                initAdapter();
            }
        });

        initScrollListener();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);
        return true;
    }

    private void populateData() {
        contentDetailSumberData.populateData(0, 10);
    }

    private void initAdapter() {
        recyclerView.setAdapter(sumberDataDetailAdapter);
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
                                    sumberDataDetailAdapter.getItemCount() - 1) {
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
                sumberDataDetailAdapter.detailItemList.remove(sumberDataDetailAdapter.getItemCount() - 1);
                int scrollPosition = sumberDataDetailAdapter.getItemCount();
                sumberDataDetailAdapter.notifyItemRemoved(scrollPosition);
                int currentSize = scrollPosition;
                int nextLimit = currentSize + 10;

                contentDetailSumberData.populateData(currentSize, nextLimit);

                sumberDataDetailAdapter.notifyDataSetChanged();
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
        void onListFragmentInteraction(ContentDetailSumberData.DetailItem detailItem);
    }
}
