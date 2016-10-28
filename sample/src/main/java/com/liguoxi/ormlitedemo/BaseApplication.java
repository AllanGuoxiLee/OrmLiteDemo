package com.liguoxi.ormlitedemo;

import android.app.Application;
import android.content.Context;

import com.liguoxi.ormlitedemo.database.entities.AddressBookInfo;
import com.liguoxi.ormlitedemo.database.entities.UserInfoEntity;
import com.liguoxi.ormlitelib.DataBaseHelper;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by Li Guoxi on 2016/10/23.
 */

public class BaseApplication extends Application {

    private static DataBaseHelper dataBaseHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        setDataBaseHelper(DataBaseHelper.getInstance(this, "OrmLiteDemo.dh", 1, new Class[]{UserInfoEntity.class, AddressBookInfo.class}));
        initImageLoader(this);
    }

    public static DataBaseHelper getDataBaseHelper() {
        return dataBaseHelper;
    }

    public static void setDataBaseHelper(DataBaseHelper dataBaseHelper) {
        BaseApplication.dataBaseHelper = dataBaseHelper;
    }

    private void initImageLoader(Context context) {
        File cacheDir = StorageUtils.getOwnCacheDirectory(context, "ormlitedemo/images");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(context)
                .memoryCacheExtraOptions(480, 800)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .threadPoolSize(3)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCache(new UnlimitedDiscCache(cacheDir))
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize((int) (Runtime.getRuntime().maxMemory() / 8))
                .imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000))
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(config);
    }
}
