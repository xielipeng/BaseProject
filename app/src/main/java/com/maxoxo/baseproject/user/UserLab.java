package com.maxoxo.baseproject.user;

import com.maxoxo.baseproject.base.MyApplication;
import com.maxoxo.baseproject.utils.SPUtils;

/**
 * 用户信息管理类
 * Created by maxoxo on 2017/7/18.
 */

public class UserLab {

    public static UserModel getUserModel() {
        if (null != SPUtils.getObject(MyApplication.getContext(), "user"))
            return SPUtils.getObject(MyApplication.getContext(), "user");
        else return null;
    }

    public static void setUserModel(UserModel userModel) {
        SPUtils.putObject(MyApplication.getContext(), "user", userModel);
    }
}
