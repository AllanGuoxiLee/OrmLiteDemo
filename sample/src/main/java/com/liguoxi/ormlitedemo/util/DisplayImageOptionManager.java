package com.liguoxi.ormlitedemo.util;

import android.graphics.Bitmap;

import com.liguoxi.ormlitedemo.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

/**
 * Created by Li Guoxi on 2016/10/23.
 */

public class DisplayImageOptionManager {

    public static DisplayImageOptions preView_options = new DisplayImageOptions
            .Builder()
            .showImageOnLoading(R.mipmap.img_default)
            .showImageForEmptyUri(R.mipmap.img_default)
            .showImageOnFail(R.mipmap.img_default)
            .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .build();

    public static DisplayImageOptions avatar_options = new DisplayImageOptions
            .Builder()
            .showImageOnLoading(R.mipmap.icon_avatar_default)
            .showImageOnFail(R.mipmap.icon_avatar_default)
            .showImageForEmptyUri(R.mipmap.icon_avatar_default)
            .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .build();
}
