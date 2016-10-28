package com.liguoxi.ormlitedemo.mvp.contractor;

import com.liguoxi.ormlitedemo.database.entities.AddressBookInfo;
import com.liguoxi.ormlitedemo.mvp.view.BaseView;

import java.util.List;

/**
 * Created by Li Guoxi on 2016/10/23.
 */

public interface AddressBookListContractor {
    interface Presenter {
        void getList();
    }

    interface View extends BaseView {
        void onSuccess(List<AddressBookInfo> list);
    }
}
