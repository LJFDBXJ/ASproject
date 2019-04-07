package com.feicuiedu.gitdroid.ptrdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.feicuiedu.gitdroid.ptrdemo.components.FooterView;
import com.feicuiedu.gitdroid.ptrdemo.view.I_PtrPageView;
import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.mugen.Mugen;
import com.mugen.MugenCallbacks;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
//import in.srain.cube.views.ptr.PtrClassicFrameLayout;
//import in.srain.cube.views.ptr.PtrDefaultHandler;
//import in.srain.cube.views.ptr.PtrFrameLayout;
//import in.srain.cube.views.ptr.PtrFrameLayout;
import butterknife.OnClick;
import butterknife.Unbinder;

//     https://github.com/yuanchaocs/PtrDemo.git

public class RepoListFragment extends MvpFragment<I_PtrPageView, ReopListPresenter> implements I_PtrPageView {

    @BindView(R.id.ptrClassicFrameLayout)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.lvRepos)
    ListView listView;

    @BindView(R.id.emptyView)
    TextView emptyView;
    @BindView(R.id.errorView)
    TextView errorView;
    private Unbinder mUnbinder;

    private ArrayAdapter<String> adapter;

    private FooterView footerView; // 上拉加载更多的视图

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate( R.layout.fragment_repo_list, container, false );

    }

    @Override
    public ReopListPresenter createPresenter() {
        return new ReopListPresenter();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        ButterKnife.bind( this, view );
        mUnbinder = ButterKnife.bind( this, view );

        adapter = new ArrayAdapter<>( getContext(), android.R.layout.simple_list_item_1 );
        listView.setAdapter( adapter );
        getPresenter().loadData();
        swipeRefresh.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().loadData();
            }
        } );
        footerView = new FooterView( getContext() );


        /** 上拉加载更多(listView滑动动最后的位置了，就可以loadmore)*/
        Mugen.with( listView, new MugenCallbacks() {
            @Override
            public void onLoadMore() {
                Toast.makeText( getContext(), "加载中*=_=*", Toast.LENGTH_SHORT ).show();
                getPresenter().loadMore();
            }

            // 是否正在加载，此方法用来避免重复加载
            @Override
            public boolean isLoading() {
                return listView.getFooterViewsCount() > 0 && footerView.isLoading();
            }

            // 是否所有数据都已加载
            @Override
            public boolean hasLoadedAllItems() {
                return listView.getFooterViewsCount() > 0 && footerView.isComplete();
            }
        } ).start();
    }

    @OnClick({R.id.emptyView, R.id.errorView})
    public void autoRefresh() {
        swipeRefresh.setRefreshing( true );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    // 这是拉刷新视图的实现----------------------------------------------------------------
    @Override
    public void showContentView() {
        swipeRefresh.setRefreshing( true );
        emptyView.setVisibility( View.GONE );
        errorView.setVisibility( View.GONE );
    }

    @Override
    public void showErroView(String msg) {
        emptyView.setVisibility( View.GONE );
        errorView.setVisibility( View.VISIBLE );
    }

    @Override
    public void showEmptyView() {
        emptyView.setVisibility( View.VISIBLE );
        errorView.setVisibility( View.GONE );
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText( getContext(), msg, Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void refreshData(List<String> datas) {
        adapter.clear();
        adapter.addAll( datas );
    }

    @Override
    public void stopRefresh() {
        swipeRefresh.setRefreshing( false );
    }


    /**
     * --------------------- 这是上拉加载更多视图层实现---------------------------
     */
    @Override
    public void addMoreData(List<String> datas) {
        adapter.clear();
        adapter.addAll( datas );
    }

    @Override
    public void hideLoadMore() {
        listView.removeFooterView( footerView );
    }

    @Override
    public void showLoadMoreLoading() {
        if (listView.getFooterViewsCount() == 0) {
            listView.addFooterView( footerView );
        }
        footerView.showLoading();
    }

    @Override
    public void showLoadMoreErro(String msg) {
        if (listView.getFooterViewsCount() == 0) {
            listView.addFooterView( footerView );
        }
        footerView.showError( msg );
    }

    @Override
    public void showLoadMoreEnd() {
        if (listView.getFooterViewsCount() == 0) {
            listView.addFooterView( footerView );
        }
        footerView.showComplete();
    }
}