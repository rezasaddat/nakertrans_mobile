package com.nakertrans.master_data;

import android.os.Bundle;
import android.os.Handler;
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
import com.nakertrans.master_content.ContentProgram;

public class ProgramMain extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView recyclerView;
    ProgramAdapter programAdapter;
    ContentProgram contentProgram;
    OnListFragmentInteractionListener listener;
    SwipeRefreshLayout refreshLayout;
    ProgressBar progressBar;
    boolean isLoading = false;

    public ProgramMain(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.program_main, container, false);
        programAdapter = new ProgramAdapter(ContentProgram.ITEM_LISTS, listener);
        recyclerView = view.findViewById(R.id.rvProgramMain);
        refreshLayout = view.findViewById(R.id.swipeProgram);
        progressBar = view.findViewById(R.id.progressbar_program);

        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeColors(
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimaryDark));

        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
                if (programAdapter.getItemCount() == 0) {
                    populateData();
                }else{
                    programAdapter.clearItem();
                    populateData();
                }
                initAdapter();
            }
        });

        initScrollListener();
        return view;
    }

    private void populateData() {
        contentProgram.populateData(0, 10);
    }

    private void initAdapter() {
        recyclerView.setAdapter(programAdapter);
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
                                    programAdapter.getItemCount() - 1) {
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
                programAdapter.programList.remove(programAdapter.getItemCount() - 1);
                int scrollPosition = programAdapter.getItemCount();
                programAdapter.notifyItemRemoved(scrollPosition);
                int currentSize = scrollPosition;
                int nextLimit = currentSize + 10;

                contentProgram.populateData(currentSize, nextLimit);

                programAdapter.notifyDataSetChanged();
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
        void onListFragmentInteraction(ContentProgram.ItemList item);
    }
}
