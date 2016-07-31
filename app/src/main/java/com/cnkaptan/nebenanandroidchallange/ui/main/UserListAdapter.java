package com.cnkaptan.nebenanandroidchallange.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnkaptan.nebenanandroidchallange.R;
import com.cnkaptan.nebenanandroidchallange.model.User;
import com.cnkaptan.nebenanandroidchallange.utils.RoundedTransformation;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cnkaptan on 28/07/16.
 */
public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    List<User> users;
    Context context;

    public UserListAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    public void addMore(List<User> users){
        this.users.addAll(users);
        notifyDataSetChanged();
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_list_layout, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = getItem(position);
        holder.tvUserName.setText(user.getLogin());
        Picasso.with(context)
                .load(user.getAvatar_url())
                .transform(new RoundedTransformation(20,0))
                .fit()
                .into(holder.ivUserAvatar);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public User getItem(int position) {
        return users.get(position);
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_user_avatar)
        ImageView ivUserAvatar;
        @Bind(R.id.tv_user_name)
        TextView tvUserName;

        UserViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}
