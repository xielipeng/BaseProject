package com.maxoxo.baseproject.base.mvpbase;

import android.os.Bundle;
import android.view.View;

import com.maxoxo.baseproject.base.BaseFragment;

/**
 * .
 * Created by xielipeng on 2017/4/4.
 */

public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment {

    protected P mvpPresenter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mvpPresenter = createPresenter();
    }

    protected abstract P createPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
