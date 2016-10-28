package com.liguoxi.ormlitedemo.database.entities;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Li Guoxi on 2016/10/23.
 */

public class AddressBookInfo {
    @DatabaseField(generatedId = true)
    private int _id;

    @DatabaseField
    private String name;

    @DatabaseField
    private String phone;

    @DatabaseField
    private String photo;

    @DatabaseField
    private String userId;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
