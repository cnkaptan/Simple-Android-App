package com.cnkaptan.nebenanandroidchallange.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.cnkaptan.nebenanandroidchallange.ApiActivity;
import com.cnkaptan.nebenanandroidchallange.R;
import com.cnkaptan.nebenanandroidchallange.model.User;
import com.cnkaptan.nebenanandroidchallange.utils.ItemClickSupport;

import java.util.List;

import butterknife.Bind;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;

public class MainActivity extends ApiActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.rv_user_list)
    RecyclerView rvUserList;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLinearLayoutManager = new LinearLayoutManager(getContext());
        rvUserList.setLayoutManager(mLinearLayoutManager);
        rvUserList.addItemDecoration(new RecyclerViewSeparator(getContext(),1));
        ItemClickSupport.addTo(rvUserList).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                startActivity(BrowserActivity.newInstance(getContext(),
                        ((UserListAdapter)recyclerView.getAdapter()).getItem(position).getLogin()));
            }
        });

        githubApi.getUsers(new Callback<List<User>>() {
            @Override
            public void success(List<User> users, Response response) {
                rvUserList.setAdapter(new UserListAdapter(users,getContext()));
                List<Header> headers = response.getHeaders();
                for (Header header:headers){
                    Log.e(TAG,String.format("%s - %s",header.getName(),header.getValue()));
                }
            }

            @Override
            public void failure(RetrofitError error) {

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

    public static class RecyclerViewSeparator extends RecyclerView.ItemDecoration{

        private final int mVerticalSpaceHeight;
        private final Drawable mDivider;

        public RecyclerViewSeparator(Context context,int mVerticalSpaceHeight) {
            this.mVerticalSpaceHeight = mVerticalSpaceHeight;
            mDivider = context.getResources().getDrawable(R.drawable.line_divider);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            outRect.bottom = mVerticalSpaceHeight;
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + mDivider.getIntrinsicHeight();

                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }
}
