package com.example.gulei.rxjavaandretrofit.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import cn.gulei.library.adapter.BaseNormalAdapter;
import com.example.gulei.rxjavaandretrofit.Config;
import com.example.gulei.rxjavaandretrofit.R;
import com.example.gulei.rxjavaandretrofit.common.utils.ImageLoaderUtils;
import com.example.gulei.rxjavaandretrofit.common.utils.PrintUtils;
import com.example.gulei.rxjavaandretrofit.mvp.iview.IMainActivityView;
import com.example.gulei.rxjavaandretrofit.mvp.presenter.MainActivityPresenter;
import com.example.gulei.rxjavaandretrofit.ui.adapter.MyAdapter;
import com.example.gulei.rxjavaandretrofit.ui.base.BaseActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements IMainActivityView{

    private SimpleDraweeView iv_bg;
    private RecyclerView mRecyclerView;
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainActivityPresenter(this);
        initDefaultHeader("主页面");
        initView();
        initData();
    }
    private void initView(){
        mRecyclerView = (RecyclerView)findViewById(R.id.recycleview);
        iv_bg = (SimpleDraweeView) findViewById(R.id.iv_bg);
//        ImageLoaderUtils.INSTANCE.displayImage("res:///"+R.mipmap.bg_splash,iv_bg, Config.IMAGE_SMALL,Config.IMAGE_SMALL);
        ImageLoaderUtils.INSTANCE.displayImage("http://desk.fd.zol-img.com.cn/t_s960x600c5/g5/M00/02/05/ChMkJ1d03bCIJKmpAAiW1QZuZMAAATGhAEEKFoACJbt686.jpg",iv_bg, Config.IMAGE_BIG,Config.IMAGE_BIG);
        adapter = new MyAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
    }
    private void initData(){
        List<String> data = new ArrayList<>();
        data.add("网络交互");
        data.add("RecycleView");
        data.add("版本更新");
        data.add("图片裁剪");
        adapter.setNewData(data);
        adapter.setOnRecyclerViewItemChildClickListener(new BaseNormalAdapter.OnRecyclerViewItemChildClickListener(){
            @Override
            public void onItemChildClick(BaseNormalAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.btn_show:{
                        PrintUtils.showToast("按下按钮："+position);
                        break;
                    }
                    case R.id.card:{
                        switch (position){
                            case 0:{
                                Intent intent = new Intent(MainActivity.this,NetActivity.class);
                                startActivity(intent);
                                break;
                            }
                            case 1:{
                                Intent intent = new Intent(MainActivity.this,RecyclerviewActivity.class);
                                startActivity(intent);
                                break;
                            }
                            case 2:{
                                Intent intent = new Intent(MainActivity.this,UpdateActivity.class);
                                startActivity(intent);
                                break;
                            }
                            case 3:{
                                Intent intent = new Intent(MainActivity.this,ScanActivity.class);
                                startActivity(intent);
                                break;
                            }
                        }
                    }
                    break;
                }
            }
        });
    }
    @Override
    public void updateData(String text) {
    }


}
