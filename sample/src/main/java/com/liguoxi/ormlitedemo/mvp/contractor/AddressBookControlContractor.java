package com.liguoxi.ormlitedemo.mvp.contractor;

import com.liguoxi.ormlitedemo.mvp.view.BaseView;

/**
 * Created by Li Guoxi on 2016/10/24.
 */

public interface AddressBookControlContractor {
    interface Presenter{
        void createNewAddress(String name,String phone,String photo);
    }

    interface View extends BaseView{
        void onSuccess();
    }
}
