package com.cnkaptan.nebenanandroidchallange.ui.main;

import android.os.Bundle;

/**
 * Created by cihankaptan on 08/08/16.
 */

public interface MainContract {

    interface MainView{

        void loadMoreSuccess();
        void loadMoreFail();

        void firstDataInitializeSuccess();
        void firstDataInitializeFail();

    }

    interface MainPresenter{
        void onCreate(Bundle savedInstance);
        void onDestroy();
        void onLoadMore();
        void firstDataInitialize();
    }
}
