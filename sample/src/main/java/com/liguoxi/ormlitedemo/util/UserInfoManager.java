package com.liguoxi.ormlitedemo.util;

import com.liguoxi.ormlitedemo.database.entities.UserInfoEntity;

/**
 * Created by Li Guoxi on 2016/10/23.
 */

public class UserInfoManager {
    private UserInfoEntity userInfo;
    private static UserInfoManager instance;

    public static UserInfoManager getInstance() {
        if (instance==null){
            instance = new UserInfoManager();
        }
        return instance;
    }


    public UserInfoEntity getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoEntity userInfo) {
        this.userInfo = userInfo;
    }
}
