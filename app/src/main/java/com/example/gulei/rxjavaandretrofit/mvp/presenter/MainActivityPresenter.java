package com.example.gulei.rxjavaandretrofit.mvp.presenter;

import com.example.gulei.rxjavaandretrofit.common.entity.JsonResult;
import com.example.gulei.rxjavaandretrofit.common.entity.enums.NetCodeNormal;
import com.example.gulei.rxjavaandretrofit.common.entity.user.UserData;
import com.example.gulei.rxjavaandretrofit.common.network.HTTPHelper;
import com.example.gulei.rxjavaandretrofit.common.utils.MD5Utils;
import com.example.gulei.rxjavaandretrofit.common.utils.PrintUtils;
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
    public void method(String arg,boolean isRefresh){
        HTTPHelper.INSTANCE.postLogin("yimi1",MD5Utils.getMD5Str("123456"),REQUEST_1,this);
    }
    public void method(String arg){
        HTTPHelper.INSTANCE.postLogin("yimi1",MD5Utils.getMD5Str("123456"),REQUEST_2,this);
    }
    @Override
    public void onNext(JsonResult t, int requestType, boolean isRefresh) {
        super.onNext(t, requestType, isRefresh);
        if(NetCodeNormal.SUCCESS.getCode() == t.getReturnCode()){
            switch (requestType){
                case REQUEST_1:{
                    UserData data = (UserData) t.getData();
                    view.updateData(PrintUtils.getText(data.toString()));
                    break;
                }
                case REQUEST_2:{
                    UserData data = (UserData) t.getData();
                    view.updateData(PrintUtils.getText(data.toString()));
                    break;
                }
            }
        }
    }
}
