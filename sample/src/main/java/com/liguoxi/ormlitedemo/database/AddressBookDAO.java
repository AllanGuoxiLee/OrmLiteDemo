package com.liguoxi.ormlitedemo.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.liguoxi.ormlitedemo.BaseApplication;
import com.liguoxi.ormlitedemo.database.entities.AddressBookInfo;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Li Guoxi on 2016/10/23.
 */

public class AddressBookDAO {
    private Dao<AddressBookInfo, String> mDao;
    private Dao<AddressBookInfo, Integer> mIDDao;
    private static AddressBookDAO instance;

    public static AddressBookDAO getInstance() {
        if (instance == null) {
            instance = new AddressBookDAO();
        }
        return instance;
    }

    public boolean createOrUpdate(AddressBookInfo info) {
        Dao<AddressBookInfo, String> dao = getInstance().getDao();
        try {
            return dao.createOrUpdate(info).getNumLinesChanged() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<AddressBookInfo> getListInfo(String user_id) {
        List<AddressBookInfo> list = null;
        Dao<AddressBookInfo, String> dao = getInstance().getDao();
        QueryBuilder<AddressBookInfo, String> queryBuilder = dao.queryBuilder();
        Where<AddressBookInfo, String> where = queryBuilder.where();
        try {
            list = where.eq("userId", user_id).query();
        } catch (SQLException e) {
            e.printStackTrace();
            return list;
        }
        return list;
    }

    public AddressBookInfo getItem(int id) {
        List<AddressBookInfo> list = null;
        Dao<AddressBookInfo, Integer> dao = getInstance().getIDDao();
        QueryBuilder<AddressBookInfo, Integer> queryBuilder = dao.queryBuilder();
        Where<AddressBookInfo, Integer> where = queryBuilder.where();
        try {
            list = where.eq("_id", id).query();
            if (list != null && list.size() > 0) {
                return list.get(0);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Dao<AddressBookInfo, String> getDao() {
        if (mDao == null) {
            try {
                mDao = BaseApplication.getDataBaseHelper().getDao(AddressBookInfo.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mDao;
    }

    public Dao<AddressBookInfo, Integer> getIDDao() {
        if (mIDDao == null) {
            try {
                mIDDao = BaseApplication.getDataBaseHelper().getDao(AddressBookInfo.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mIDDao;
    }
}
