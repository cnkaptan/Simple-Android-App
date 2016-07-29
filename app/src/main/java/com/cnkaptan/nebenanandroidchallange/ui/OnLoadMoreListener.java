package com.cnkaptan.nebenanandroidchallange.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.cnkaptan.nebenanandroidchallange.model.User;

/**
 * Created by cihankaptan on 29/07/16.
 */
public abstract class OnLoadMoreListener extends RecyclerView.OnScrollListener {
    private static final String TAG = OnLoadMoreListener.class.getSimpleName();
    private LinearLayoutManager mLinearLayoutManager;
    private static final int VISIBLE_TRESHOLD = 5;
    public boolean isLoading;
    public abstract void onLoadMore(int userId);
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        mLinearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int totalItemCount = mLinearLayoutManager.getItemCount();
        int lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();

        if (!isLoading && totalItemCount <= (lastVisibleItem + VISIBLE_TRESHOLD)) {
            UserListAdapter userListAdapter = (UserListAdapter) recyclerView.getAdapter();
            User user = userListAdapter.getItem(totalItemCount-1);
            Log.e(TAG,"onLoadMore");
            onLoadMore(user.getId());
        }
    }
}
