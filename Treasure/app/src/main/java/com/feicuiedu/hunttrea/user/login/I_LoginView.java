package com.feicuiedu.hunttrea.user.login;

/**
 * Created by gqq on 2017/1/3.
 */

// 登录的视图接口
public interface I_LoginView {

    void showProgress();
    void hideProgress();
    void showMessage(String msg);
    void navigationToHome();

}
