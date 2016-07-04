package com.example.gulei.rxjavaandretrofit.mvp.presenter;


import android.os.Handler;

import com.example.gulei.rxjavaandretrofit.mvp.iview.IRecycleviewActivityView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gulei on 2016/7/4 0004.
 */
public class RecycleviewActivityPresenter extends BasePresenter {
    private final int REQUEST_1 = 0x1001;
    private IRecycleviewActivityView view;

    public RecycleviewActivityPresenter(IRecycleviewActivityView view) {
        super(view);
        this.view = view;
    }
    public void requestData(final int pageNo, final boolean isRefresh){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<String> data = new ArrayList<>();
                int i=0;
                while(i<10){
                    data.add("测试数据："+(pageNo*10+i++));
                }
                view.updateView(data,isRefresh);
            }
        },2000);

     }
}
