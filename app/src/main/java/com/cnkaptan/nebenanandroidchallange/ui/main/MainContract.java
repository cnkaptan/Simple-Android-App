package com.cnkaptan.nebenanandroidchallange.ui.main;

import android.os.Bundle;

import com.cnkaptan.nebenanandroidchallange.model.User;

import java.util.List;

/**
 * Created by cihankaptan on 08/08/16.
 */

public interface MainContract {

    interface MainView{

        void loadMoreSuccess(List<User> users);
        void loadServiceFail(Throwable t);
        void firstDataInitializeSuccess(List<User> users);

    }

    interface MainPresenter{
        void onCreate(Bundle savedInstance);
        void onDestroy();
        void onLoadMore(int id);
        void firstDataInitialize();
        void setView(MainView mainView);
    }
}
