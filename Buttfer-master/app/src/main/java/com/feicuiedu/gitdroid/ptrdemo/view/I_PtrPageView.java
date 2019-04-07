package com.feicuiedu.gitdroid.ptrdemo.view;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

/**
 * Created by Administrator on 2016/7/1 0001.
 */
public interface I_PtrPageView extends MvpView,I_PtrView<List<String>>, I_LoadMoreView<List<String>> {
}
