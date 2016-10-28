package com.liguoxi.ormlitelib;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Li Guoxi on 2016/10/22.
 */

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {

    private static String mDatabaseName;
    private static int mDatabaseVersion;
    private static DataBaseHelper instance;

    private Class<?>[] mTables;

    public static DataBaseHelper getInstance(Context context, String databaseName, int databaseVersion, Class<?>[] tables) {
        mDatabaseName = databaseName;
        mDatabaseVersion = databaseVersion;
        if (instance == null || !instance.isOpen()) {
            instance = new DataBaseHelper(context);
        }
        instance.mTables = tables;
        return instance;
    }

    public DataBaseHelper(Context context) {
        super(context, mDatabaseName, null, mDatabaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            for (Class<?> cz : mTables) {
                TableUtils.createTableIfNotExists(connectionSource, cz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            sqLiteDatabase.execSQL("ALTER TABLE download ADD COLUMN path text DEFAULT '';");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTable(Class<?> cls) {
        try {
            TableUtils.createTableIfNotExists(getConnectionSource(), cls);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
