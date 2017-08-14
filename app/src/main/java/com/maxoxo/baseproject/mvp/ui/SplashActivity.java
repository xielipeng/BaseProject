package com.maxoxo.baseproject.mvp.ui;

import android.content.Intent;
import android.os.Bundle;

import com.maxoxo.baseproject.R;
import com.maxoxo.baseproject.base.BaseActivityNoTitle;
import com.maxoxo.baseproject.utils.ConstantUtil;
import com.maxoxo.baseproject.utils.SPUtils;
import com.maxoxo.baseproject.utils.SystemUiVisibilityUtil;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * 闪屏启动页
 *
 * @author maxoxo 2017-8-11
 */
public class SplashActivity extends BaseActivityNoTitle {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean isFirstOpen = (boolean) SPUtils.get(this, ConstantUtil.FIRST_OPEN, true);
        if (isFirstOpen) {
            // 进入轮播页
        }
        //如果不是第一次启动，则显示起屏页
        setContentView(R.layout.activity_splash);
        SystemUiVisibilityUtil.hideStatusBar(getWindow(), true);
        setUpSplash();
    }

    private void setUpSplash() {
        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .compose(bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> finishTask());
    }

    private void finishTask() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
