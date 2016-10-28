package com.liguoxi.ormlitedemo.mvp.presenter;

import com.liguoxi.ormlitedemo.database.UserInfoDAO;
import com.liguoxi.ormlitedemo.database.entities.UserInfoEntity;
import com.liguoxi.ormlitedemo.mvp.contractor.LoginContractor;
import com.liguoxi.ormlitedemo.util.UserInfoManager;

/**
 * Created by Li Guoxi on 2016/10/23.
 */

public class LoginPresenter implements LoginContractor, LoginContractor.Presenter {

    private View mView;

    public LoginPresenter(View view) {
        this.mView = view;
    }

    @Override
    public void Login(String username, String password) {
        UserInfoEntity entity = UserInfoDAO.getInstance().getUserInfoBuyUserName(username);
        if (entity == null) {
            mView.onFail("用户不存在");
            return;
        } else if (!entity.getPassword().equals(password)) {
            mView.onFail("密码错误");
            return;
        } else {

            UserInfoManager.getInstance().setUserInfo(entity);
            mView.onSuccess();
        }

    }

    @Override
    public void signIn(String username, String password) {
        if (UserInfoDAO.getInstance().getUserInfoBuyUserName(username) != null) {
            mView.onFail("用户已存在");
        } else {
            int userId;
            if (UserInfoDAO.getInstance().getUserInfoList() != null && UserInfoDAO.getInstance().getUserInfoList().size() > 0) {
                int[] userids = new int[UserInfoDAO.getInstance().getUserInfoList().size()];
                for (int i = 0; i < UserInfoDAO.getInstance().getUserInfoList().size(); i++) {
                    userids[i] = Integer.parseInt(UserInfoDAO.getInstance().getUserInfoList().get(i).getUser_id());
                }
                userId = getRandomUserId(userids);
            } else {
                userId = (int) (Math.random() * 10000);
            }
            UserInfoEntity entity = new UserInfoEntity();
            entity.setPassword(password);
            entity.setUser_id(String.valueOf(userId));
            entity.setUserName(username);
            if (UserInfoDAO.getInstance().createOrUpdate(entity)) {
                UserInfoManager.getInstance().setUserInfo(entity);
                mView.onSuccess();
            }

        }
    }

    private int getRandomUserId(int[] ids) {
        int user_id_random = (int) (Math.random() * 10000);


        for (int i : ids) {
            if (i == user_id_random) {
                return getRandomUserId(ids);
            }
        }

        return user_id_random;
    }
}
