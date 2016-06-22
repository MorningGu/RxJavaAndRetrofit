package com.example.gulei.rxjavaandretrofit.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gulei.rxjavaandretrofit.Config;
import com.example.gulei.rxjavaandretrofit.R;
import com.example.gulei.rxjavaandretrofit.common.utils.ImageLoaderUtils;
import com.example.gulei.rxjavaandretrofit.mvp.iview.IMainActivityView;
import com.example.gulei.rxjavaandretrofit.mvp.presenter.MainActivityPresenter;
import com.example.gulei.rxjavaandretrofit.ui.base.BaseActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MainActivity extends BaseActivity implements IMainActivityView,SwipeRefreshLayout.OnRefreshListener{
    private TextView tv_data;
    private Button btn_start;
    private SimpleDraweeView iv_bg;
    private RecyclerView mRecyclerView;
    private MainActivityPresenter presenter;
    private MyAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainActivityPresenter(this);
        initView();
    }
    private void initView(){
        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeLayout);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycleview);
        tv_data = (TextView) findViewById(R.id.tv_data);
        btn_start = (Button)findViewById(R.id.btn_start);
        iv_bg = (SimpleDraweeView) findViewById(R.id.iv_bg);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        //设置刷新时动画的颜色，可以设置4个
        // 顶部刷新的样式
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        ImageLoaderUtils.INSTANCE.displayImage("res:///"+R.mipmap.bg_splash,iv_bg, Config.IMAGE_SMALL,Config.IMAGE_SMALL);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.method(null);
            }
        });
        adapter = new MyAdapter(this,null);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
    }
    @Override
    public void updateData(String text) {
        mSwipeRefreshLayout.setRefreshing(false);
        tv_data.setText(text);
    }

    @Override
    public void onRefresh() {
        presenter.method(null,true);
    }
    public class MyAdapter extends BaseQuickAdapter<String> {
        public MyAdapter(Context context, List<String> datas) {
            super(context, R.layout.common_header, datas);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item, int position) {

        }
    }
}
