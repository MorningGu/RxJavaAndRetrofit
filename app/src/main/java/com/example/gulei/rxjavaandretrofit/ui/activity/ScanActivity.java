package com.example.gulei.rxjavaandretrofit.ui.activity;

import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.example.gulei.rxjavaandretrofit.R;
import com.example.gulei.rxjavaandretrofit.ui.base.BaseActivity;

import cn.gulei.scanlibrary.scan.IScanner;
import cn.gulei.scanlibrary.scan.OpenCVHelper;
import cn.gulei.scanlibrary.scan.PickImageFragment;
import cn.gulei.scanlibrary.scan.ResultFragment;
import cn.gulei.scanlibrary.scan.ScanConstants;
import cn.gulei.scanlibrary.scan.ScanFragment;


/**
 * Created by jhansi on 28/03/15.
 */
public class ScanActivity extends BaseActivity implements IScanner {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_layout);
        init();
    }

    private void init() {
        PickImageFragment fragment = new PickImageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ScanConstants.OPEN_INTENT_PREFERENCE, getPreferenceContent());
        fragment.setArguments(bundle);
        android.app.FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content, fragment);
        fragmentTransaction.commit();
    }

    protected int getPreferenceContent() {
        return getIntent().getIntExtra(ScanConstants.OPEN_INTENT_PREFERENCE, 0);
    }

    @Override
    public void onBitmapSelect(Uri uri) {
        ScanFragment fragment = new ScanFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ScanConstants.SELECTED_BITMAP, uri);
        fragment.setArguments(bundle);
        android.app.FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content, fragment);
        fragmentTransaction.addToBackStack(ScanFragment.class.toString());
        fragmentTransaction.commit();
    }

    @Override
    public void onScanFinish(Uri uri) {
        ResultFragment fragment = new ResultFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ScanConstants.SCANNED_RESULT, uri);
        fragment.setArguments(bundle);
        android.app.FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content, fragment);
        fragmentTransaction.addToBackStack(ResultFragment.class.toString());
        fragmentTransaction.commit();
    }

    public Bitmap getScannedBitmap(Bitmap bitmap, float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4){
        return new OpenCVHelper().getScannedBitmap(bitmap,x1,y1,x2,y2,x3,y3,x4,y4);
    }

    public float[] getPoints(Bitmap bitmap){
        return new OpenCVHelper().getPoints(bitmap);
    }

}