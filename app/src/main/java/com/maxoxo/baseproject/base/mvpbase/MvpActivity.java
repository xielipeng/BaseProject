package com.maxoxo.baseproject.base.mvpbase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.maxoxo.baseproject.base.BaseActivity;

/**
 * 。
 * Created by xielipeng on 2017/4/4.
 */

public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {

    protected P mvpPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null)
            mvpPresenter.detachView();
    }

    public void showLoading() {
        showLoading();
    }

    public void showLoading(String msg) {
        showLoading(msg);
    }

    public void showLoading(String msg, boolean cancelable) {
        showLoading(msg, cancelable);
    }

    public void hideLoading() {
        dismissLoading();
    }

    public void showNullLayout() {

    }

    public void hideNullLayout() {

    }

    public void showErrorLayout(View.OnClickListener listener) {

    }

    public void hideErrorLayout() {

    }

}
