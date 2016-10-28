package com.liguoxi.ormlitedemo.util;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.liguoxi.ormlitedemo.R;


/**
 * Created by Li Guoxi on 2016/7/27.
 */
public class SnackBarUtil {
    private static SnackBarUtil instance;
    private View view;
    Snackbar mSnackBar;

    private int mColor = -1;

    private ColorStateList mColorStateList;

    public SnackBarUtil(View view) {
        this.view = view;
    }

    public static SnackBarUtil getInstance(View view) {
        instance = new SnackBarUtil(view);
        return instance;
    }

    public SnackBarUtil showLong(CharSequence message) {
        mSnackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        return this;
    }

    public SnackBarUtil showLong(int messageId) {
        mSnackBar = Snackbar.make(view, messageId, Snackbar.LENGTH_LONG);
        return this;
    }

    public SnackBarUtil showShort(CharSequence message) {
        mSnackBar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        return this;
    }

    public SnackBarUtil showShort(int msgId) {
        mSnackBar = Snackbar.make(view, msgId, Snackbar.LENGTH_SHORT);
        return this;
    }


    public SnackBarUtil setAction(CharSequence actionMsg, View.OnClickListener onClickListener) {
        mSnackBar.setAction(actionMsg, onClickListener);
        return this;
    }

    public SnackBarUtil setAction(int actionMsgId, View.OnClickListener onClickListener) {
        mSnackBar.setAction(actionMsgId, onClickListener);
        return this;
    }

    public SnackBarUtil setActionTextColor(int color) {
        mSnackBar.setActionTextColor(color);
        return this;
    }

    public SnackBarUtil setActionTextColor(ColorStateList color) {
        mSnackBar.setActionTextColor(color);
        return this;
    }

    public SnackBarUtil setMessageColor(int color) {
        mColor = color;
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) mSnackBar.getView();
        ((TextView) snackbarLayout.findViewById(R.id.snackbar_text)).setTextColor(color);
        return this;
    }

    public SnackBarUtil setMessageColor(ColorStateList color) {
        mColorStateList = color;
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) mSnackBar.getView();
        ((TextView) snackbarLayout.findViewById(R.id.snackbar_text)).setTextColor(color);
        return this;
    }


    public void show() {
        if (mColor == -1 && mColorStateList == null) {
            Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) mSnackBar.getView();
            ((TextView) snackbarLayout.findViewById(R.id.snackbar_text)).setTextColor(Color.WHITE);
        }
        mSnackBar.show();
    }

}
