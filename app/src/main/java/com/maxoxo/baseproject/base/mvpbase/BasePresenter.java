package com.maxoxo.baseproject.base.mvpbase;

import com.maxoxo.baseproject.net.retrofit.ApiClient;
import com.maxoxo.baseproject.net.retrofit.ApiStores;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * BasePresenter里面去初始化View对象，同时提供释放View对象以防止内存溢出
 * Created by xielipeng on 2017/4/4.
 */

public class BasePresenter<V> {

    public V mvpView;
    protected ApiStores mApiStores;
    private CompositeSubscription mCompositeSubscription;

    public void attachView(V mvpView) {
        this.mvpView = mvpView;
        mApiStores = ApiClient.retrofit().create(ApiStores.class);
    }

    public void detachView() {
        this.mvpView = null;
        onUnsubscribe();
    }

    /**
     * rxjava取消注册，以避免内存泄漏
     */
    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions())
            mCompositeSubscription.unsubscribe();
    }

    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null)
            mCompositeSubscription = new CompositeSubscription();

        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }
}
