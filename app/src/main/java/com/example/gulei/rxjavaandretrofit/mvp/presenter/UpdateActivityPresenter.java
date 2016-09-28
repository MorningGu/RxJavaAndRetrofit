package com.example.gulei.rxjavaandretrofit.mvp.presenter;

import com.example.gulei.rxjavaandretrofit.common.network.ApiManager;
import com.example.gulei.rxjavaandretrofit.mvp.entity.JsonResult;
import com.example.gulei.rxjavaandretrofit.mvp.entity.Version;
import com.example.gulei.rxjavaandretrofit.mvp.iview.IUpdateActivityView;
import com.trello.rxlifecycle.LifecycleTransformer;

/**
 * Created by gulei on 2016/7/7 0007.
 */
public class UpdateActivityPresenter extends BasePresenter<IUpdateActivityView> {
    private final int REQUEST_UPDATE = 0x1001;
    public void checkUpdate(String arg, LifecycleTransformer former){
        ApiManager.INSTANCE.startObservable(ApiManager.INSTANCE.getINetInterface().postCheckUpdate(),REQUEST_UPDATE,false,former,this);
    }

    @Override
    public void onNext(JsonResult t, int requestType, boolean isRefresh) {
        super.onNext(t, requestType, isRefresh);
        switch (requestType){
            case REQUEST_UPDATE:
                IUpdateActivityView view = getView();
                if(view!=null){
                    Version data = (Version)t.getData();
                    view.updateData("地址："+data.getVersion().getServiceAddress());
                    view.showUpdateDialog(false,data.getVersion().getServiceAddress());
                }
                break;
        }
    }
}
