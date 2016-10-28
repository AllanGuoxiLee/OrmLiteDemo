package com.liguoxi.ormlitedemo.util;

import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

/**
 * Created by Li Guoxi on 2016/9/23.
 */

public class ImageDisplayOptions {
    private static ImageDisplayOptions instance;

    private int imageRes;

    public ImageDisplayOptions(int imageRes) {
        this.imageRes = imageRes;
    }


    public static ImageDisplayOptions getInstance(@DrawableRes int imageRes) {
        instance = new ImageDisplayOptions(imageRes);
        return instance;
    }

    public final DisplayImageOptions options(){
        return new DisplayImageOptions
                .Builder()
                .showImageOnLoading(imageRes)
                .showImageOnFail(imageRes)
                .showImageForEmptyUri(imageRes)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }
}
