package com.cnkaptan.nebenanandroidchallange.ui.detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cnkaptan.nebenanandroidchallange.R;
import com.cnkaptan.nebenanandroidchallange.model.Repo;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cnkaptan on 30/07/16.
 */
public class DetailListAdapter extends RecyclerView.Adapter<DetailListAdapter.DetailViewHolder> {

    private final Context context;
    List<Repo> userRepos;

    public DetailListAdapter(List<Repo> userRepos, Context context) {
        this.userRepos = userRepos;
        this.context = context;
    }

    public void addRepos(List<Repo> userRepos) {
        this.userRepos.addAll(userRepos);
        notifyDataSetChanged();
    }

    @Override
    public DetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_list_item_layout, parent, false);

        return new DetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DetailViewHolder holder, int position) {
        Repo repo = userRepos.get(position);
        holder.tvRepoName.setText(repo.getName());
        if (!TextUtils.isEmpty(repo.getDescription())){
            holder.tvRepoDescription.setText(repo.getDescription());
        }else{
            holder.tvRepoDescription.setVisibility(View.GONE);
        }
        holder.tvRepoLanguage.setText(repo.getLanguage());
        holder.tvRepoCounts.setText(String.format(context.getResources().getString(R.string.repo_counts_format),
                repo.getWatchers_count(),
                repo.getStargazers_count(),
                repo.getForks_count()));
    }


    public Repo getItem(int position){
        return userRepos.get(position);
    }

    @Override
    public int getItemCount() {
        return userRepos.size();
    }

    public static class DetailViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_repo_name)
        TextView tvRepoName;
        @Bind(R.id.tv_repo_description)
        TextView tvRepoDescription;
        @Bind(R.id.tv_repo_language)
        TextView tvRepoLanguage;
        @Bind(R.id.tv_repo_counts)
        TextView tvRepoCounts;
        public DetailViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }

}
