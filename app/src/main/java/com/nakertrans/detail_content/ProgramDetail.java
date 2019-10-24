package com.nakertrans.detail_content;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.nakertrans.R;

public class ProgramDetail extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView recyclerView;
    ProgramDetailAdapter programDetailAdapter;
    ContentDetailProgram contentDetailProgram;
    OnListFragmentInteractionListener mListener;
    SwipeRefreshLayout refreshLayout;
    ProgressBar progressBar;
    boolean isLoading = false;

    public ProgramDetail(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.program_detail);

        programDetailAdapter = new ProgramDetailAdapter(ContentDetailProgram.DETAIL_ITEM_LIST, mListener);
        recyclerView = findViewById(R.id.listProgramsDetail);
        refreshLayout = findViewById(R.id.swipeDetailProgram);
        progressBar = findViewById(R.id.programDetailProgressBar);

        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeColors(
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimaryDark));

        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
                if (programDetailAdapter.getItemCount() == 0) {
                    populateData();
                }else{
                    programDetailAdapter.clearItem();
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

    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(true);
        initAdapter();
    }

    private void populateData() {
        contentDetailProgram.populateData(0, 10);
    }

    private void initAdapter() {
        recyclerView.setAdapter(programDetailAdapter);
        Log.d("ADAPTER", String.valueOf(programDetailAdapter.detailItemList.size()));
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
                                    programDetailAdapter.getItemCount() - 1) {
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
                programDetailAdapter.detailItemList.remove(programDetailAdapter.getItemCount() - 1);
                int scrollPosition = programDetailAdapter.getItemCount();
                programDetailAdapter.notifyItemRemoved(scrollPosition);
                int currentSize = scrollPosition;
                int nextLimit = currentSize + 10;

                contentDetailProgram.populateData(currentSize, nextLimit);

                programDetailAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.INVISIBLE);
            }
        }, 2000);
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(ContentDetailProgram.ProgramDetailItem detailItem);
    }
}
