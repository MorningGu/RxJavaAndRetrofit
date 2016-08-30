package com.example.gulei.rxjavaandretrofit.mvp.presenter;

import com.example.gulei.rxjavaandretrofit.mvp.iview.IMainActivityView;

/**
 * Created by gulei on 2016/6/17 0017.
 */
public class MainActivityPresenter extends BasePresenter {
    private final int REQUEST_1 = 0x1001;
    private final int REQUEST_2 = 0x1002;
    private IMainActivityView view;
    public MainActivityPresenter(IMainActivityView view) {
        super(view);
        this.view = view;
    }

}
