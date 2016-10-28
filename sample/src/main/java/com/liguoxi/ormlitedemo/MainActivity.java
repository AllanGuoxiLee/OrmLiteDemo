package com.liguoxi.ormlitedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.liguoxi.ormlitedemo.mvp.contractor.LoginContractor;
import com.liguoxi.ormlitedemo.mvp.presenter.LoginPresenter;
import com.liguoxi.ormlitedemo.util.SnackBarUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LoginContractor.View {

    private Toolbar toolbar;
    private EditText username_edt, password_edt;
    private Button login_btn, signIn_btn;

    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        mPresenter = new LoginPresenter(this);
        initViews();
    }

    private void initViews() {
        username_edt = (EditText) findViewById(R.id.username_edt);
        password_edt = (EditText) findViewById(R.id.password_edt);
        login_btn = (Button) findViewById(R.id.login_btn);
        signIn_btn = (Button) findViewById(R.id.signIn_btn);
        login_btn.setOnClickListener(this);
        signIn_btn.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.fast_login:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View view) {
        String username = username_edt.getText().toString();
        String password = password_edt.getText().toString();
        switch (view.getId()) {
            case R.id.login_btn:
                if (username == null || username.length() == 0) {
                    onFail("请输入用户名");
                    return;
                }
                if (password_edt == null || password.length() == 0) {
                    onFail("请输入密码");
                    return;
                }

                mPresenter.Login(username, password);
                break;

            case R.id.signIn_btn:
                if (username == null || username.length() == 0) {
                    onFail("请输入用户名");
                    return;
                }
                if (password_edt == null || password.length() == 0) {
                    onFail("请输入密码");
                    return;
                }

                mPresenter.signIn(username, password);
                break;
        }
    }

    @Override
    public void onSuccess() {
        startActivity(new Intent(this, AddressBookActivity.class));
    }

    @Override
    public void onFail(String errorMessage) {
        SnackBarUtil.getInstance(toolbar).showShort(errorMessage).show();
    }
}
