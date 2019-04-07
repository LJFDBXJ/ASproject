package com.feicuiedu.hunttrea.user.account;

/**
 * Created by gqq on 2017/1/13.
 */

public interface I_AccountView {

    void showProgress();

    void hideProgress();

    void showMessage(String msg);

    void updatePhoto(String photoUrl);
}
