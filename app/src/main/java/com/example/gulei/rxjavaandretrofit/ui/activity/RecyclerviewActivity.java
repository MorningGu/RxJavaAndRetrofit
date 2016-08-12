package com.example.gulei.rxjavaandretrofit.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import cn.gulei.library.adapter.BaseNormalAdapter;
import com.example.gulei.rxjavaandretrofit.R;
import com.example.gulei.rxjavaandretrofit.mvp.iview.IRecycleviewActivityView;
import com.example.gulei.rxjavaandretrofit.mvp.presenter.RecycleviewActivityPresenter;
import com.example.gulei.rxjavaandretrofit.ui.adapter.MyAdapter;
import com.example.gulei.rxjavaandretrofit.ui.base.BaseActivity;

import java.util.List;

public class RecyclerviewActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,IRecycleviewActivityView {
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private MyAdapter adapter;
    private int pageNo = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        initDefaultHeader("RecyclerView");
        presenter = new RecycleviewActivityPresenter(this);
        initView();
    }
    private void initView(){
        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeLayout);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycleview);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        //设置刷新时动画的颜色，可以设置4个
        // 顶部刷新的样式
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        adapter = new MyAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
        adapter.setPageSize(10);
        adapter.openLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseNormalAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                ((RecycleviewActivityPresenter)presenter).requestData(pageNo+1,false);
            }
        });
    }

    @Override
    public void onRefresh() {
        ((RecycleviewActivityPresenter)presenter).requestData(pageNo,true);
    }

    @Override
    public void updateView(List<String> list,boolean isRefresh) {
        mSwipeRefreshLayout.setRefreshing(false);
        if(isRefresh){
            pageNo = 0;
            adapter.setNewData(list);
        }else{
            pageNo++;
            adapter.notifyDataChangedAfterLoadMore(list);
        }
    }

}
