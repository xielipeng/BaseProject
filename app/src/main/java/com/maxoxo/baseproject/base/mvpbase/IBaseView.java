package com.maxoxo.baseproject.base.mvpbase;

import android.view.View;

/**
 * View层里的一些常用的方法我们也可以封到base里面
 * Created by xielipeng on 2017/4/4.
 */

public interface IBaseView {

    void showLoading();

    void showLoading(String msg);

    void showLoading(String msg, boolean cancelable);

    void hideLoading();

    /**
     * 显示空数据布局
     */
    void showNullLayout();

    void hideNullLayout();

    /**
     * 显示异常布局
     */
    void showErrorLayout(View.OnClickListener listener);

    void hideErrorLayout();
}
