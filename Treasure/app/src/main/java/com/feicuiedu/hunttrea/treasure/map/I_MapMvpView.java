package com.feicuiedu.hunttrea.treasure.map;

import com.feicuiedu.hunttrea.treasure.Treasure;

import java.util.List;

/**
 * Created by gqq on 2017/1/10.
 */

public interface I_MapMvpView {

    void showMessage(String msg);// 弹吐司
    void setData(List<Treasure> list);// 设置数据
}
