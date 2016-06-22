package com.example.gulei.rxjavaandretrofit.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gulei.rxjavaandretrofit.R;

/**
 * Created by gulei on 2016/4/29 0029.
 */
public class HeadLayout extends RelativeLayout {
    //左边按钮
    private RelativeLayout btn_left;
    //左边图片
    private ImageView iv_left;
    //左边文字
    private TextView tv_left;
    //右边按钮
    private RelativeLayout btn_right;
    //右边图片
    private ImageView iv_right;
    //右边文字
    private TextView tv_right;
    //中间标题
    private TextView tv_center;
    //左边的点击事件
    private OnLeftClickListener mLeftOnclickListener;
    //右边的点击事件
    private OnRightClickListener mRightOnclickListener;

    public HeadLayout(Context context) {
        super(context);
    }

    public HeadLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HeadLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 初始化控件 在外部调用
     */
    public void initView(){
        btn_left = (RelativeLayout)findViewById(R.id.btn_header_left);
        btn_right = (RelativeLayout)findViewById(R.id.btn_header_right);
        iv_left = (ImageView) findViewById(R.id.iv_header_left);
        iv_right = (ImageView) findViewById(R.id.iv_header_right);
        tv_left = (TextView) findViewById(R.id.tv_header_left);
        tv_right = (TextView) findViewById(R.id.tv_header_right);
        tv_center = (TextView) findViewById(R.id.tv_header_center);
    }

    /**
     * 左边图片
     * @param resourceId
     * @param title
     * @param listener
     */
    public void initTitleAndLeftImage(String title,int resourceId,OnLeftClickListener listener){
        iv_right.setVisibility(GONE);
        tv_right.setVisibility(GONE);
        iv_left.setVisibility(VISIBLE);
        tv_left.setVisibility(GONE);
        tv_center.setText(title);
        iv_left.setImageResource(resourceId);
        setOnLeftClickListener(listener);
    }
    /**
     * 左边文字
     * @param leftText
     * @param title
     * @param listener
     */
    public void initTitleAndLeftText(String title,String leftText,OnLeftClickListener listener){
        iv_right.setVisibility(GONE);
        tv_right.setVisibility(GONE);
        iv_left.setVisibility(GONE);
        tv_left.setVisibility(VISIBLE);
        tv_center.setText(title);
        tv_left.setText(leftText);
        setOnLeftClickListener(listener);
    }
    /**
     * 右边图片
     * @param resourceId
     * @param title
     * @param listener
     */
    public void initTitleAndRightImage(String title,int resourceId,OnRightClickListener listener){
        iv_right.setVisibility(VISIBLE);
        tv_right.setVisibility(GONE);
        iv_left.setVisibility(GONE);
        tv_left.setVisibility(GONE);
        tv_center.setText(title);
        iv_right.setImageResource(resourceId);
        setOnRightClickListener(listener);
    }
    /**
     * 左边文字
     * @param leftText
     * @param title
     * @param listener
     */
    public void initTitleAndLeftText(String title,String leftText,OnRightClickListener listener){
        iv_right.setVisibility(GONE);
        tv_right.setVisibility(VISIBLE);
        iv_left.setVisibility(GONE);
        tv_left.setVisibility(GONE);
        tv_center.setText(title);
        tv_right.setText(leftText);
        setOnRightClickListener(listener);
    }

    /**
     * 左边图片右边图片
     * @param title
     * @param leftResourceId
     * @param rightResourceId
     * @param leftListener
     * @param rightListener
     */
    public void initTitleAndBothImage(String title,int leftResourceId,int rightResourceId,
                                      OnLeftClickListener leftListener,OnRightClickListener rightListener){
        iv_right.setVisibility(VISIBLE);
        tv_right.setVisibility(GONE);
        iv_left.setVisibility(VISIBLE);
        tv_left.setVisibility(GONE);
        tv_center.setText(title);
        iv_left.setImageResource(leftResourceId);
        iv_right.setImageResource(rightResourceId);
        setOnLeftClickListener(leftListener);
        setOnRightClickListener(rightListener);
    }

    /**
     * 两边都是文字
     * @param title
     * @param leftText
     * @param rightText
     * @param leftListener
     * @param rightListener
     */
    public void initTitleAndBothText(String title,String leftText,String rightText,
                                      OnLeftClickListener leftListener,OnRightClickListener rightListener){
        iv_right.setVisibility(GONE);
        tv_right.setVisibility(VISIBLE);
        iv_left.setVisibility(GONE);
        tv_left.setVisibility(VISIBLE);
        tv_center.setText(title);
        tv_left.setText(leftText);
        tv_right.setText(rightText);
        setOnLeftClickListener(leftListener);
        setOnRightClickListener(rightListener);
    }

    /**
     * 左边图片右边文字
     * @param title
     * @param leftResourceId
     * @param rightText
     * @param leftListener
     * @param rightListener
     */
    public void initTitleAndImageText(String title,int leftResourceId,String rightText,
                                      OnLeftClickListener leftListener,OnRightClickListener rightListener){
        iv_right.setVisibility(GONE);
        tv_right.setVisibility(VISIBLE);
        iv_left.setVisibility(VISIBLE);
        tv_left.setVisibility(GONE);
        tv_center.setText(title);
        iv_left.setImageResource(leftResourceId);
        tv_right.setText(rightText);
        setOnLeftClickListener(leftListener);
        setOnRightClickListener(rightListener);
    }

    /**
     * 左边文字右边图片
     * @param title
     * @param leftText
     * @param rightResourceId
     * @param leftListener
     * @param rightListener
     */
    public void initTitleAndTextImage(String title,String leftText,int rightResourceId,
                                      OnLeftClickListener leftListener,OnRightClickListener rightListener){
        iv_right.setVisibility(VISIBLE);
        tv_right.setVisibility(GONE);
        iv_left.setVisibility(GONE);
        tv_left.setVisibility(VISIBLE);
        tv_center.setText(title);
        tv_left.setText(leftText);
        iv_right.setImageResource(rightResourceId);
        setOnLeftClickListener(leftListener);
        setOnRightClickListener(rightListener);
    }

    /**
     * 为左边按钮设置点击事件
     * @param listener
     */
    public void setOnLeftClickListener(HeadLayout.OnLeftClickListener listener){
        mLeftOnclickListener = listener;
        btn_left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mLeftOnclickListener!=null){
                    mLeftOnclickListener.onClick();
                }
            }
        });
    }

    /**
     * 为右边按钮设置点击事件
     * @param listener
     */
    public void setOnRightClickListener(HeadLayout.OnRightClickListener listener){
        mRightOnclickListener = listener;
        btn_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mRightOnclickListener!=null){
                    mRightOnclickListener.onClick();
                }
            }
        });
    }

    /**
     * 设置背景
     * @param resourceId
     */
    public void setBackground(int resourceId){
        setBackgroundResource(resourceId);
    }
    public interface OnLeftClickListener {
        void onClick();
    }
    public interface OnRightClickListener {
        void onClick();
    }
}
