package com.example.gulei.rxjavaandretrofit.mvp.iview;

import java.util.List;

/**
 * Created by gulei on 2016/7/4 0004.
 */
public interface IRecycleviewActivityView extends IBaseView {
    void updateView(List<String> list,boolean isRefresh);
}
