package com.example.gulei.rxjavaandretrofit.mvp.presenter;

import com.example.gulei.rxjavaandretrofit.common.entity.JsonResult;
import com.example.gulei.rxjavaandretrofit.common.entity.Version;
import com.example.gulei.rxjavaandretrofit.common.network.HTTPHelper;
import com.example.gulei.rxjavaandretrofit.mvp.iview.IUpdateActivityView;
import com.trello.rxlifecycle.LifecycleTransformer;

import java.util.Map;

/**
 * Created by gulei on 2016/7/7 0007.
 */
public class UpdateActivityPresenter extends BasePresenter {
    private final int REQUEST_UPDATE = 0x1001;
    private IUpdateActivityView view;
    public UpdateActivityPresenter(IUpdateActivityView view) {
        super(view);
        this.view = view;
    }
    public void checkUpdate(String arg, LifecycleTransformer former){
        HTTPHelper.INSTANCE.postCheckUpdate(REQUEST_UPDATE,false,former,this);
    }

    @Override
    public void onNext(JsonResult t, int requestType, boolean isRefresh) {
        super.onNext(t, requestType, isRefresh);
        switch (requestType){
            case REQUEST_UPDATE:
                Version data = (Version)t.getData();
                view.updateData("地址："+data.getVersion().getServiceAddress());
                view.showUpdateDialog(false,data.getVersion().getServiceAddress());
                break;
        }
    }
}
