package com.maxoxo.baseproject.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.maxoxo.baseproject.net.retrofit.ApiClient;
import com.maxoxo.baseproject.net.retrofit.ApiStores;
import com.maxoxo.baseproject.utils.AppManager;
import com.maxoxo.baseproject.widgets.NormalProgressDialog;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by maxoxo on 2017/7/30.
 */

public class BaseActivityNoTitle extends RxAppCompatActivity {
    private static final String TAG = "BaseActivityNoTitle";

    public Activity mActivity;


    public ApiStores apiStores = ApiClient.retrofit().create(ApiStores.class);
    private CompositeSubscription mCompositeSubscription;

    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    public void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    public void onUnsubscribe() {
        //取消注册，以避免内存泄露
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions())
            mCompositeSubscription.unsubscribe();
    }


    protected void showLoading() {
        NormalProgressDialog.showLoading(mActivity);
    }

    protected void showLoading(CharSequence sequence) {
        NormalProgressDialog.showLoading(mActivity, sequence);
    }

    protected void showLoading(CharSequence sequence, boolean cancelable) {
        NormalProgressDialog.showLoading(mActivity, sequence, cancelable);
    }

    protected void dismissLoading() {
        NormalProgressDialog.dismissLoading();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: " + this.getLocalClassName());
        AppManager.getAppManager().addActivity(this);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        mActivity = this;
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
        mActivity = this;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: " + this.getLocalClassName());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: " + this.getLocalClassName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: " + this.getLocalClassName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: " + this.getLocalClassName());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: " + this.getLocalClassName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: " + this.getLocalClassName());
        onUnsubscribe();
        AppManager.getAppManager().finishActivity(this);
    }

}
