package com.example.gulei.rxjavaandretrofit.mvp.presenter;

import com.example.gulei.rxjavaandretrofit.common.network.ApiManager;
import com.example.gulei.rxjavaandretrofit.mvp.entity.Emoji;
import com.example.gulei.rxjavaandretrofit.mvp.entity.JsonResult;
import com.example.gulei.rxjavaandretrofit.mvp.entity.enums.NetCodeNormal;
import com.example.gulei.rxjavaandretrofit.mvp.entity.user.UserData;

import cn.gulei.library.utils.LogUtils;
import cn.gulei.library.utils.MD5Utils;
import com.example.gulei.rxjavaandretrofit.common.utils.ToastUtils;
import com.example.gulei.rxjavaandretrofit.mvp.iview.INetActivityView;
import com.trello.rxlifecycle.LifecycleTransformer;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by gulei on 2016/7/4 0004.
 */
public class NetActivityPresenter extends BasePresenter<INetActivityView> {
    private final int REQUEST_1 = 0x1001;
    private final int REQUEST_2 = 0x1002;
    private final int REQUEST_3 = 0x1003;
    private final int REQUEST_4 = 0x1004;

    public NetActivityPresenter() {
    }
    public void upload(String arg, boolean isRefresh, LifecycleTransformer former){
        File file = new File("/sdcard/Download/1111.jpg");
        RequestBody body =  RequestBody.create(MediaType.parse("multipart/form-data"), file);
        ApiManager.INSTANCE.startObservable(ApiManager.INSTANCE.getINetInterface().postUpload(body,"1","android"),REQUEST_1,false,former,this);
    }
    public void method(String arg, LifecycleTransformer former){
        ApiManager.INSTANCE.startObservable(ApiManager.INSTANCE.getINetInterface().postLogin("yimi1",MD5Utils.getMD5Str("123456")), REQUEST_2,false,former,this);
    }
    public void sendMessage(String msg,LifecycleTransformer former){
        ApiManager.INSTANCE.startObservable(ApiManager.INSTANCE.getINetInterface().postSendMessage(msg),REQUEST_3,false,former,this);
    }
    public void requestMessage(LifecycleTransformer former){
        ApiManager.INSTANCE.startObservable(ApiManager.INSTANCE.getINetInterface().postRequestMessage(),REQUEST_4,false,former,this);
    }
    @Override
    public void onNext(JsonResult t, int requestType, boolean isRefresh) {
        super.onNext(t, requestType, isRefresh);
        if(NetCodeNormal.SUCCESS.getCode() == t.getReturnCode()){
            INetActivityView view = getView();
            switch (requestType){
                case REQUEST_1:{
                    Map<String,String> data = (Map<String,String>) t.getData();
                    ToastUtils.showToast("上传完成");
                    LogUtils.d("上传请求","上传完成");
                    break;
                }
                case REQUEST_2:{
                    UserData data = (UserData) t.getData();
                    getView().updateData(ToastUtils.getText(data.toString()));
                    break;
                }
                case REQUEST_3:{
//                    view.updateData(ToastUtils.getText(new String(Base64.decode(data.getWord(),Base64.DEFAULT))));
                    if(view!=null){
                        Emoji data = (Emoji) t.getData();
                        view.updateData(ToastUtils.getText(data.getWord()));
                    }

                    break;
                }
                case REQUEST_4: {
                    if(view!=null){
                        Emoji data = (Emoji) t.getData();
                        view.updateData(ToastUtils.getText(data.getWord()));
                    }
                    break;
                }
            }
        }
    }
}
