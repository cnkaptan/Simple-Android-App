package com.cnkaptan.nebenanandroidchallange.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cnkaptan.nebenanandroidchallange.BaseActivity;
import com.cnkaptan.nebenanandroidchallange.GithubApplication;
import com.cnkaptan.nebenanandroidchallange.R;
import com.cnkaptan.nebenanandroidchallange.model.User;
import com.cnkaptan.nebenanandroidchallange.ui.browser.BrowserActivity;
import com.cnkaptan.nebenanandroidchallange.ui.detail.UserDetailActivity;
import com.cnkaptan.nebenanandroidchallange.utils.DialogUtils;
import com.cnkaptan.nebenanandroidchallange.utils.ItemClickSupport;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import rx.Subscription;

public class MainActivity extends BaseActivity implements MainContract.MainView,ItemClickSupport.OnItemClickListener, ItemClickSupport.OnItemLongClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.rv_user_list)
    RecyclerView rvUserList;
    private LinearLayoutManager mLinearLayoutManager;
    private UserListAdapter adapter;
    Subscription subscriptionUsersPagination;

    @Inject
    MainContract.MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((GithubApplication)getApplication()).getAppComponent().inject(this);
        mainPresenter.setView(this);


        mLinearLayoutManager = new LinearLayoutManager(getContext());
        rvUserList.setLayoutManager(mLinearLayoutManager);
        ItemClickSupport.addTo(rvUserList).setOnItemClickListener(this);
        ItemClickSupport.addTo(rvUserList).setOnItemLongClickListener(this);

        mainPresenter.firstDataInitialize();

        rvUserList.addOnScrollListener(new OnLoadMoreListener(mLinearLayoutManager) {
            @Override
            public void onLoadMore(int userId) {
                mainPresenter.onLoadMore(userId);
            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public Context getContext() {
        return this;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ItemClickSupport.removeFrom(rvUserList);
        rvUserList.addOnScrollListener(null);
        mainPresenter.onDestroy();
    }

    @Override
    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
        startActivity(UserDetailActivity.newInstance(getContext(),
                ((UserListAdapter) recyclerView.getAdapter()).getItem(position).getLogin()));
    }

    @Override
    public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
        startActivity(BrowserActivity.newInstance(getContext(),
                ((UserListAdapter) recyclerView.getAdapter()).getItem(position).getLogin(),
                BrowserActivity.USERNAME));
        return false;
    }

    @Override
    public void loadMoreSuccess(List<User> users) {
        adapter.addMore(users);
    }

    @Override
    public void loadServiceFail(Throwable t) {
        DialogUtils.showGeneralErrorDialog(getContext(), errorDefine(t));
    }

    @Override
    public void firstDataInitializeSuccess(List<User> users) {
        adapter = new UserListAdapter(users, getContext());
        rvUserList.setAdapter(adapter);
    }

}
