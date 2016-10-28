package com.liguoxi.ormlitedemo.adapter.recyclerBaseAdapter;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.liguoxi.ormlitedemo.R;
import com.liguoxi.ormlitedemo.ToolBarCompatActivity;
import com.liguoxi.ormlitedemo.mvp.contractor.AddressBookControlContractor;
import com.liguoxi.ormlitedemo.mvp.presenter.AddressControlPresenter;
import com.liguoxi.ormlitedemo.util.DisplayImageOptionManager;
import com.liguoxi.ormlitedemo.util.ImagePickerUtil;
import com.liguoxi.ormlitedemo.util.SnackBarUtil;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridBottomSheet;
import com.lzy.imagepicker.ui.ImageGridBottomSheetFragment;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CreateNewAddressActivity extends ToolBarCompatActivity implements View.OnClickListener, AddressBookControlContractor.View {

    @Bind(R.id.avatar_iv)
    ImageView avatarIv;
    @Bind(R.id.name_edt)
    EditText nameEdt;
    @Bind(R.id.phone_edt)
    EditText phoneEdt;
    @Bind(R.id.add_btn)
    Button addBtn;
    @Bind(R.id.activity_create_new_address)
    RelativeLayout activityCreateNewAddress;

    private AddressControlPresenter mPresenter;

    private String mPhotoURI;

    @Override
    protected void onCreateCustomToolBar(Toolbar toolbar) {
        super.onCreateCustomToolBar(toolbar);
        toolbar.setTitle("添加联系人");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_address);
        ButterKnife.bind(this);
        mPresenter = new AddressControlPresenter(this);
        initView();
    }

    private void initView() {
        avatarIv.setOnClickListener(this);
        addBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.avatar_iv:
                ImagePickerUtil.getInstance(mContext).initAvatarPicker();
                ImageGridBottomSheetFragment.getInstance(new ImageGridBottomSheet.ImageBottomSheetListener() {
                    @Override
                    public void getImages(ArrayList<ImageItem> imageItems) {
                        ImageItem item = imageItems.get(0);
                        mPhotoURI = item.path;
                        ImageLoader.getInstance().displayImage("file://" + mPhotoURI, avatarIv, DisplayImageOptionManager.avatar_options);
                    }
                }).show(getSupportFragmentManager(), "ImageGridBottomSheetFragment");
                break;

            case R.id.add_btn:
                String name = nameEdt.getText().toString();
                String phone = phoneEdt.getText().toString();
                if (name == null || name.length() == 0) {
                    onFail("请输入名称");
                    return;
                }

                if (phone==null||phone.length()==0){
                    onFail("请输入联系号码");
                    return;
                }

                mPresenter.createNewAddress(name,phone,mPhotoURI);
                break;
        }
    }

    @Override
    public void onSuccess() {
        finishAct();
    }

    @Override
    public void onFail(String errorMessage) {
        SnackBarUtil.getInstance(activityCreateNewAddress).showShort(errorMessage).show();
    }
}
