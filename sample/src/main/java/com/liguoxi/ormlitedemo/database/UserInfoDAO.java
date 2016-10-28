package com.liguoxi.ormlitedemo.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.liguoxi.ormlitedemo.BaseApplication;
import com.liguoxi.ormlitedemo.database.entities.UserInfoEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Li Guoxi on 2016/10/22.
 */

public class UserInfoDAO {

    private static UserInfoDAO instance;


    private Dao<UserInfoEntity, String> cacheDao;

    public static UserInfoDAO getInstance() {
        if (instance == null) {
            instance = new UserInfoDAO();
        }
        return instance;
    }

    public boolean createOrUpdate(UserInfoEntity info) {
        try {
            Dao<UserInfoEntity, String> dao = getInstance().getCacheDao();
            return dao.createOrUpdate(info).getNumLinesChanged() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<UserInfoEntity> getUserInfoList() {
        Dao<UserInfoEntity, String> dao = getInstance().getCacheDao();
        List<UserInfoEntity> list = null;
        QueryBuilder<UserInfoEntity, String> queryBuilder = dao.queryBuilder();
        Where<UserInfoEntity, String> where = queryBuilder.where();
        try {
            list = where.isNotNull("userName").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public UserInfoEntity getUserInfoBuyUserName(String userName) {
        UserInfoEntity entity = null;
        List<UserInfoEntity> list = null;
        Dao<UserInfoEntity, String> dao = getInstance().getCacheDao();
        QueryBuilder<UserInfoEntity, String> queryBuilder = dao.queryBuilder();
        Where<UserInfoEntity, String> where = queryBuilder.where();
        try {
            list = where.eq("userName", userName).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (list != null && list.size() > 0) {
            entity = list.get(0);
        }
        return entity;
    }


    public Dao<UserInfoEntity, String> getCacheDao() {
        if (cacheDao == null) {
            try {
                cacheDao = BaseApplication.getDataBaseHelper().getDao(UserInfoEntity.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return cacheDao;
    }

    public void setCacheDao(Dao<UserInfoEntity, String> cacheDao) {
        this.cacheDao = cacheDao;
    }
}
