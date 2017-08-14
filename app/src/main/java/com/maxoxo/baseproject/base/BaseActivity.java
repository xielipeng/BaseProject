package com.maxoxo.baseproject.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.maxoxo.baseproject.R;
import com.maxoxo.baseproject.net.retrofit.ApiClient;
import com.maxoxo.baseproject.net.retrofit.ApiStores;
import com.maxoxo.baseproject.utils.AppManager;
import com.maxoxo.baseproject.widgets.NormalProgressDialog;
import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * .
 * Created by maxoxo on 2017/7/16.
 */

public class BaseActivity extends RxAppCompatActivity {
    private static final String TAG = "BaseActivity";

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


    private TextView mTitleTextView;
    private ImageView mBackImageView;

    protected void setTitle(String msg) {
        if (mTitleTextView != null)
            mTitleTextView.setText(msg);
    }

    protected void setBack() {
        if (mBackImageView != null) {
            mBackImageView.setVisibility(View.VISIBLE);
            RxView.clicks(mBackImageView).subscribe(new Action1<Void>() {
                @Override
                public void call(Void aVoid) {
                    finish();
                }
            });
        }
    }

    protected void setBackClickListener(View.OnClickListener l) {
        if (mBackImageView != null) {
            mBackImageView.setVisibility(View.VISIBLE);
            mBackImageView.setOnClickListener(l);
        } else
            Logger.t(TAG).e("back is null ,please check out");
    }

    private LinearLayout rootLayout;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        setContentView(View.inflate(this, layoutResID, null));
        ButterKnife.bind(this);
        mActivity = this;
    }

    @Override
    public void setContentView(View view) {
        rootLayout = (LinearLayout) findViewById(R.id.root_layout);
        if (rootLayout == null) return;
        rootLayout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ButterKnife.bind(this);
        mActivity = this;
        initToolbar();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: " + this.getLocalClassName());
        AppManager.getAppManager().addActivity(this);
        // 经测试在代码里直接声明透明状态栏更有效
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }*/
        // 这句很关键，注意是调用父类的方法
        super.setContentView(R.layout.activity_base);
        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.common_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        if (getSupportActionBar() != null) {
            // Enable the Up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        }
        mBackImageView = (ImageView) findViewById(R.id.topBack);
        mTitleTextView = (TextView) findViewById(R.id.title);
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
