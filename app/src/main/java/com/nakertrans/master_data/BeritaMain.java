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
import com.nakertrans.master_content.ContentBerita;

public class BeritaMain extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView recyclerView;
    BeritaAdapter beritaAdapter;
    ContentBerita contentBerita;
    OnListFragmentInteractionListener listener;
    SwipeRefreshLayout refreshLayout;
    ProgressBar progressBar;
    boolean isLoading = false;

    public BeritaMain(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.berita_main, container, false);
        beritaAdapter = new BeritaAdapter(ContentBerita.ITEM_LISTS, listener);
        recyclerView = view.findViewById(R.id.rvBeritaMain);
        refreshLayout = view.findViewById(R.id.swipeBerita);
        progressBar = view.findViewById(R.id.progressbar_berita);

        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeColors(
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimaryDark));

        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
                if (beritaAdapter.getItemCount() == 0) {
                    populateData();
                }else{
                    beritaAdapter.clearItem();
                    populateData();
                }
                initAdapter();
            }
        });

        initScrollListener();
        return view;
    }

    private void populateData() {
        contentBerita.populateData(0, 10);
    }

    private void initAdapter() {
        recyclerView.setAdapter(beritaAdapter);
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
                                    beritaAdapter.getItemCount() - 1) {
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
                beritaAdapter.beritaList.remove(beritaAdapter.getItemCount() - 1);
                int scrollPosition = beritaAdapter.getItemCount();
                beritaAdapter.notifyItemRemoved(scrollPosition);
                int currentSize = scrollPosition;
                int nextLimit = currentSize + 10;

                contentBerita.populateData(currentSize, nextLimit);

                beritaAdapter.notifyDataSetChanged();
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
        void onListFragmentInteraction(ContentBerita.ItemList item);
    }
}
