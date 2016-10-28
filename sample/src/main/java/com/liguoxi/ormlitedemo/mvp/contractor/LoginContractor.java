package com.liguoxi.ormlitedemo.mvp.contractor;

import com.liguoxi.ormlitedemo.mvp.view.BaseView;

/**
 * Created by Li Guoxi on 2016/10/23.
 */

public interface LoginContractor {
    interface Presenter {
        void Login(String username, String password);
        void signIn(String username,String password);
    }

    interface View extends BaseView{
        void onSuccess();
    }
}
