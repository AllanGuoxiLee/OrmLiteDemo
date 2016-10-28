package com.liguoxi.ormlitedemo.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.liguoxi.ormlitedemo.R;


/**
 * Created by Li Guoxi on 2016/5/30.
 */
public class ToolBarUtil {
    private Context context;
    private FrameLayout contentView;
    private View userView;
    private TextView title_tv;
    private Toolbar toolbar;
    private View toolbarDividerLine;
    private LayoutInflater inflater;

    private static int[] ATTRS = {
            android.support.v7.appcompat.R.attr.windowActionBarOverlay,
            android.support.v7.appcompat.R.attr.actionBarSize
    };

    public ToolBarUtil(Context context, int layoutID) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        initContentView();
        initUserView(layoutID);
        initToolBar();
    }

    private void initContentView() {
        contentView = new FrameLayout(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        contentView.setLayoutParams(params);
    }

    private void initToolBar() {
        View toolBar = inflater.inflate(R.layout.toolbar_layout, contentView);
        toolbar = (Toolbar) toolBar.findViewById(R.id.tool_bar);
        title_tv = (TextView) toolBar.findViewById(R.id.title_tv);
        toolbarDividerLine = toolBar.findViewById(R.id.toolbar_divider_line);
    }

    @SuppressWarnings("ResourceType")
    private void initUserView(int layoutID) {
        userView = inflater.inflate(layoutID, null);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(ATTRS);

        boolean overly = typedArray.getBoolean(0, false);

        int toolBarSize = (int) typedArray.getDimension(1, context.getResources().getDimension(R.dimen.abc_action_bar_default_height_material));
        typedArray.recycle();
        params.topMargin = overly ? 0 : toolBarSize;
        contentView.addView(userView, params);
    }

    public FrameLayout getContentView() {
        return contentView;
    }

    public TextView getTitle_tv() {
        return title_tv;
    }

    public View getToolBarDividerLine() {
        return toolbarDividerLine;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }
}
