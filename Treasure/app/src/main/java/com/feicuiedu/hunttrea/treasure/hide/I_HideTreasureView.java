package com.feicuiedu.hunttrea.treasure.hide;

/**
 * Created by gqq on 2017/1/12.
 */

public interface I_HideTreasureView {

    // 宝藏上传中视图的交互

    void showProgress();

    void hideProgress();

    void showMessage(String msg);

    void navigationToHome();

}
