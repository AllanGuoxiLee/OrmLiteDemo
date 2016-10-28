package com.liguoxi.ormlitedemo.mvp.presenter;

import com.liguoxi.ormlitedemo.database.AddressBookDAO;
import com.liguoxi.ormlitedemo.database.entities.AddressBookInfo;
import com.liguoxi.ormlitedemo.mvp.contractor.AddressBookControlContractor;
import com.liguoxi.ormlitedemo.util.UserInfoManager;

/**
 * Created by Li Guoxi on 2016/10/24.
 */

public class AddressControlPresenter implements AddressBookControlContractor, AddressBookControlContractor.Presenter {
    private View mView;

    public AddressControlPresenter(View view) {
        this.mView = view;
    }

    @Override
    public void createNewAddress(String name, String phone, String photo) {
        AddressBookInfo info = new AddressBookInfo();
        info.setName(name);
        info.setPhone(phone);
        info.setPhoto(photo);
        info.setUserId(UserInfoManager.getInstance().getUserInfo().getUser_id());
        if (AddressBookDAO.getInstance().createOrUpdate(info)) {
            mView.onSuccess();
        }
    }
}
