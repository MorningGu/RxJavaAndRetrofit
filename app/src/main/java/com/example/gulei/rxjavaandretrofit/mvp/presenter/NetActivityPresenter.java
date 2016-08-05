package com.example.gulei.rxjavaandretrofit.mvp.presenter;

import android.util.Base64;

import com.example.gulei.rxjavaandretrofit.common.entity.Emoji;
import com.example.gulei.rxjavaandretrofit.common.entity.JsonResult;
import com.example.gulei.rxjavaandretrofit.common.entity.enums.NetCodeNormal;
import com.example.gulei.rxjavaandretrofit.common.entity.user.UserData;
import com.example.gulei.rxjavaandretrofit.common.network.HTTPHelper;
import com.example.gulei.rxjavaandretrofit.common.utils.MD5Utils;
import com.example.gulei.rxjavaandretrofit.common.utils.PrintUtils;
import com.example.gulei.rxjavaandretrofit.mvp.iview.INetActivityView;
import com.trello.rxlifecycle.LifecycleTransformer;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by gulei on 2016/7/4 0004.
 */
public class NetActivityPresenter extends BasePresenter {
    private final int REQUEST_1 = 0x1001;
    private final int REQUEST_2 = 0x1002;
    private final int REQUEST_3 = 0x1003;
    private final int REQUEST_4 = 0x1004;
    private INetActivityView view;

    public NetActivityPresenter(INetActivityView view) {
        super(view);
        this.view = view;
    }
    public void upload(String arg, boolean isRefresh, LifecycleTransformer former){
        File file = new File("/sdcard/Download/1111.jpg");
        RequestBody body =  RequestBody.create(MediaType.parse("multipart/form-data"), file);
        HTTPHelper.INSTANCE.postUpload(body,REQUEST_1,isRefresh,former,this);
    }
    public void method(String arg, LifecycleTransformer former){
        HTTPHelper.INSTANCE.postLogin("yimi1",MD5Utils.getMD5Str("123456"),REQUEST_2,false,former,this);
    }
    public void sendMessage(String msg,LifecycleTransformer former){
        HTTPHelper.INSTANCE.postSendMessage(REQUEST_3,false,former,msg,this);
    }
    public void requestMessage(LifecycleTransformer former){
        HTTPHelper.INSTANCE.postRequestMessage(REQUEST_4,false,former,this);
    }
    @Override
    public void onNext(JsonResult t, int requestType, boolean isRefresh) {
        super.onNext(t, requestType, isRefresh);
        if(NetCodeNormal.SUCCESS.getCode() == t.getReturnCode()){
            switch (requestType){
                case REQUEST_1:{
                    Map<String,String> data = (Map<String,String>) t.getData();
                    PrintUtils.showToast("上传完成");
                    PrintUtils.d("上传请求","上传完成");
                    break;
                }
                case REQUEST_2:{
                    UserData data = (UserData) t.getData();
                    view.updateData(PrintUtils.getText(data.toString()));
                    break;
                }
                case REQUEST_3:{
                    Emoji data = (Emoji) t.getData();
//                    view.updateData(PrintUtils.getText(new String(Base64.decode(data.getWord(),Base64.DEFAULT))));
                    view.updateData(PrintUtils.getText(data.getWord()));
                    break;
                }
                case REQUEST_4: {
                    Emoji data = (Emoji) t.getData();
                    view.updateData(PrintUtils.getText(data.getWord()));
                    break;
                }
            }
        }
    }
}
