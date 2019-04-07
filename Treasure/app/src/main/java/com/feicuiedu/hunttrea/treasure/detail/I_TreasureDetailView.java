package com.feicuiedu.hunttrea.treasure.detail;

import java.util.List;

/**
 * Created by gqq on 2017/1/11.
 */

// 宝藏详情获取的视图接口
public interface I_TreasureDetailView {

    // 显示信息
    void showMessage(String msg);

    // 设置数据
    void setData(List<TreasureDetailResult> resultList);

}
