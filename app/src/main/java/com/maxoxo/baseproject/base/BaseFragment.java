package com.maxoxo.baseproject.base;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maxoxo.baseproject.net.retrofit.ApiClient;
import com.maxoxo.baseproject.net.retrofit.ApiStores;
import com.maxoxo.baseproject.widgets.NormalProgressDialog;

import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * .
 * Created by maxoxo on 2017/7/19.
 */

public class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";
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
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach: " + this.getClass().getName());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: " + this.getClass().getName());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: " + this.getClass().getName());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated: " + this.getClass().getName());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated: " + this.getClass().getName());
        ButterKnife.bind(this, view);
        mActivity = getActivity();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: " + this.getClass().getName());
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: " + this.getClass().getName());
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: " + this.getClass().getName());
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: " + this.getClass().getName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView: " + this.getClass().getName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: " + this.getClass().getName());
        onUnsubscribe();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "onDetach: " + this.getClass().getName());
    }

}
