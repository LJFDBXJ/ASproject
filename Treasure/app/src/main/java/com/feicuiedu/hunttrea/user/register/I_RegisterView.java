package com.feicuiedu.hunttrea.user.register;

/**
 * Created by gqq on 2017/1/3.
 */

// 视图的接口
public interface I_RegisterView {

    void showProgress();

    void hideProgress();

    void showMessage(String msg);

    void navigationToHome();

}
