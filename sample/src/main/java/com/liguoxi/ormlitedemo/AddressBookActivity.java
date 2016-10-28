package com.liguoxi.ormlitedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.liguoxi.ormlitedemo.adapter.recyclerBaseAdapter.BaseQuickAdapter;
import com.liguoxi.ormlitedemo.adapter.recyclerBaseAdapter.BaseViewHolder;
import com.liguoxi.ormlitedemo.adapter.recyclerBaseAdapter.CreateNewAddressActivity;
import com.liguoxi.ormlitedemo.database.entities.AddressBookInfo;
import com.liguoxi.ormlitedemo.mvp.contractor.AddressBookListContractor;
import com.liguoxi.ormlitedemo.mvp.presenter.GetAddressBookPresenter;
import com.liguoxi.ormlitedemo.util.DisplayImageOptionManager;
import com.liguoxi.ormlitedemo.util.SnackBarUtil;
import com.liguoxi.ormlitedemo.util.UserInfoManager;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddressBookActivity extends ToolBarCompatActivity implements AddressBookListContractor.View {

    @Bind(R.id.recycler)
    RecyclerView recycler;
    @Bind(R.id.activity_address_book)
    RelativeLayout activityAddressBook;

    private View emptyView;

    private BaseQuickAdapter<AddressBookInfo> mAdapter;

    private GetAddressBookPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_book);
        ButterKnife.bind(this);
        toolbar.setTitle(UserInfoManager.getInstance().getUserInfo().getUserName());
        mPresenter = new GetAddressBookPresenter(this);
        initViews();
    }

    private void initViews() {
        recycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mAdapter = new BaseQuickAdapter<AddressBookInfo>(mContext, R.layout.address_book_item, new ArrayList<AddressBookInfo>()) {
            @Override
            protected void convert(BaseViewHolder helper, AddressBookInfo item) {
                helper.setText(R.id.name_tv, item.getName());
                helper.setText(R.id.phone_tv, item.getPhone());
                String photoURL = "file://" + item.getPhoto();
                ImageLoader.getInstance().displayImage(photoURL, (ImageView) helper.getView(R.id.avatar_iv), DisplayImageOptionManager.avatar_options);
            }
        };
        emptyView = getLayoutInflater().inflate(R.layout.empty_list_view, (ViewGroup) recycler.getParent(), false);
        recycler.setAdapter(mAdapter);
        mAdapter.setEmptyView(emptyView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                startActivity(new Intent(mContext, CreateNewAddressActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getList();
    }

    @Override
    public void onSuccess(List<AddressBookInfo> list) {
        mAdapter.setEmptyView(null);
        mAdapter.setNewData(list);
    }

    @Override
    public void onFail(String errorMessage) {
        SnackBarUtil.getInstance(activityAddressBook).showShort(errorMessage).show();
    }
}
