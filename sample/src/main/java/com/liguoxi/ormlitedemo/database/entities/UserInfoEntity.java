package com.liguoxi.ormlitedemo.database.entities;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Li Guoxi on 2016/10/22.
 */

public class UserInfoEntity {
    @DatabaseField(generatedId = true)
    private int _id;

    @DatabaseField
    private String userName;

    @DatabaseField
    private String password;

    @DatabaseField
    private String user_id;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
