package com.maxoxo.baseproject.mvp.presenter;

import com.maxoxo.baseproject.base.mvpbase.BasePresenter;
import com.maxoxo.baseproject.mvp.view.IMainView;

/**
 * Created by maxoxo on 2017/8/11.
 */

public class MainPresenter extends BasePresenter<IMainView> {
    public MainPresenter(IMainView view) {
        attachView(view);
    }
}
