package com.example.gulei.rxjavaandretrofit.ui.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gulei.rxjavaandretrofit.R;

/**
 * Created by gulei on 2016/7/4 0004.
 */
public class MyAdapter extends BaseQuickAdapter<String> {
    public MyAdapter(Context context) {
        super(context,R.layout.item_cardview,null);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item, int position) {
            helper.setOnClickListener(R.id.card,new OnItemChildClickListener())
                    .setOnClickListener(R.id.btn_show,new OnItemChildClickListener())
                    .setText(R.id.tv_title,item);
    }
}
