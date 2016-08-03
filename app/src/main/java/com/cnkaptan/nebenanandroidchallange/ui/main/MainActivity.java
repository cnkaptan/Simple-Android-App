package com.cnkaptan.nebenanandroidchallange.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.cnkaptan.nebenanandroidchallange.ApiActivity;
import com.cnkaptan.nebenanandroidchallange.R;
import com.cnkaptan.nebenanandroidchallange.model.User;
import com.cnkaptan.nebenanandroidchallange.ui.browser.BrowserActivity;
import com.cnkaptan.nebenanandroidchallange.ui.detail.UserDetailActivity;
import com.cnkaptan.nebenanandroidchallange.utils.DialogUtils;
import com.cnkaptan.nebenanandroidchallange.utils.ItemClickSupport;

import java.util.List;

import butterknife.Bind;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends ApiActivity implements ItemClickSupport.OnItemClickListener,ItemClickSupport.OnItemLongClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.rv_user_list)
    RecyclerView rvUserList;
    private LinearLayoutManager mLinearLayoutManager;
    private static String nextUrl;
    private UserListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLinearLayoutManager = new LinearLayoutManager(getContext());
        rvUserList.setLayoutManager(mLinearLayoutManager);
        ItemClickSupport.addTo(rvUserList).setOnItemClickListener(this);
        ItemClickSupport.addTo(rvUserList).setOnItemLongClickListener(this);



        rvUserList.addOnScrollListener(new OnLoadMoreListener(mLinearLayoutManager) {
            @Override
            public void onLoadMore(int userId) {
                Log.e(TAG,""+userId);

                githubApi.getObservableUsersPaginationById(userId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<User>>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                DialogUtils.showGeneralErrorDialog(getContext(),errorDefine(e));
                            }

                            @Override
                            public void onNext(List<User> users) {
                                adapter.addMore(users);
                            }
                        });

            }
        });


        githubApi.getObservableUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<User>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        DialogUtils.showGeneralErrorDialog(getContext(),errorDefine(e));
                    }

                    @Override
                    public void onNext(List<User> users) {
                        adapter = new UserListAdapter(users, getContext());
                        rvUserList.setAdapter(adapter);
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
    }

    @Override
    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
        startActivity(UserDetailActivity.newInstance(getContext(),
                ((UserListAdapter)recyclerView.getAdapter()).getItem(position).getLogin()));
    }

    @Override
    public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
        startActivity(BrowserActivity.newInstance(getContext(),
                ((UserListAdapter)recyclerView.getAdapter()).getItem(position).getLogin(),
                BrowserActivity.USERNAME));
        return false;
    }
}
