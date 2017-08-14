package com.maxoxo.baseproject.mvp.ui;

import android.os.Bundle;

import com.maxoxo.baseproject.R;
import com.maxoxo.baseproject.base.mvpbase.MvpActivity;
import com.maxoxo.baseproject.mvp.presenter.MainPresenter;
import com.maxoxo.baseproject.mvp.view.IMainView;

public class MainActivity extends MvpActivity<MainPresenter> implements IMainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }


}
