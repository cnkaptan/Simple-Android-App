package com.cnkaptan.nebenanandroidchallange.ui.detail;

import com.cnkaptan.nebenanandroidchallange.model.DetailedUser;
import com.cnkaptan.nebenanandroidchallange.model.Repo;

import java.util.List;

/**
 * Created by cnkaptan on 09/08/16.
 */
public interface UserDetailContract {

    interface UserDetailView{
        void getReposSuccess(List<Repo> repos);
        void getDetailedUserSuccess(DetailedUser detailedUser);
        void onRequestFail(Throwable t);
    }

    interface UserDetailPresenter{
        void setView(UserDetailView userDetailView);
        void initDatas(String userName);
        void onDestroy();
    }
}
