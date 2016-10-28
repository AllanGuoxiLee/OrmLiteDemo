package com.liguoxi.ormlitedemo.mvp.presenter;

import com.liguoxi.ormlitedemo.database.AddressBookDAO;
import com.liguoxi.ormlitedemo.database.entities.AddressBookInfo;
import com.liguoxi.ormlitedemo.mvp.contractor.AddressBookListContractor;
import com.liguoxi.ormlitedemo.util.UserInfoManager;

import java.util.List;

/**
 * Created by Li Guoxi on 2016/10/23.
 */

public class GetAddressBookPresenter implements AddressBookListContractor, AddressBookListContractor.Presenter {
    private View mView;

    public GetAddressBookPresenter(View view) {
        this.mView = view;
    }

    @Override
    public void getList() {
        List<AddressBookInfo> list = null;
        list = AddressBookDAO.getInstance().getListInfo(UserInfoManager.getInstance().getUserInfo().getUser_id());
        if (list != null && list.size() > 0) {
            mView.onSuccess(list);
        }else {
            mView.onFail("没有数据");
        }
    }
}
