package com.example.asus.newsdemo1.Presenter;

import android.content.pm.ProviderInfo;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by codekk on 2016/4/8.
 * Email:  645326280@qq.com
 */
public class MyOnScrollListener extends RecyclerView.OnScrollListener {
    private ScrollCallback callback;
    private int lastVisibleItemPosition;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager layoutManager;

    public interface ScrollCallback {
        public void onBottomLoading();
    }

    public MyOnScrollListener(ScrollCallback callback1) {
        callback = callback1;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        adapter = recyclerView.getAdapter();
        layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
        Log.d("KKLog", "!!!!MyOnScrollListener lastVisibleItemPosition===>"+lastVisibleItemPosition);
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
        Log.d("KKLog", "!!!!MyOnScrollListener firstVisibleItemPosition===>"+firstVisibleItemPosition);
        if (lastVisibleItemPosition - 1 == 0) {
            Log.d("KKLog", "!!!!MyOnScrollListener onHeadLoading!!!!!");
            callback.onBottomLoading();
            adapter.notifyItemRemoved(adapter.getItemCount());
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        Log.d("KKLog", "StateChanged = " + newState);
        if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == adapter.getItemCount()) {
            Log.d("KKLog", "!!!!MyOnScrollListener onBottomLoading!!!!!");
            callback.onBottomLoading();
        }
    }

}
