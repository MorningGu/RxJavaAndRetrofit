package com.example.gulei.rxjavaandretrofit.ui.adapter;

import android.content.Context;

import cn.gulei.library.adapter.BaseQuickAdapter;
import cn.gulei.library.adapter.BaseViewHolder;

import com.example.gulei.rxjavaandretrofit.R;

/**
 * Created by gulei on 2016/7/4 0004.
 */
public class MyAdapter extends BaseQuickAdapter<String> {
    public MyAdapter(Context context) {
        super(R.layout.item_cardview,null);
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.addOnClickListener(R.id.btn_show)
                .setText(R.id.tv_title,item);
    }
}
