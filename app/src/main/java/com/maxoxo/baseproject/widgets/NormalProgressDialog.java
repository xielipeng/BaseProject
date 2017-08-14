package com.maxoxo.baseproject.widgets;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.lang.ref.WeakReference;

/**
 * 全局ProgressDialog的封装，主要处理手机返回键等触发Dialog消失，应该取消正在进行的网络请求等
 * Created by maxoxo on 2017/7/18.
 */

public class NormalProgressDialog extends ProgressDialog implements DialogInterface.OnCancelListener {

    private WeakReference<Context> mContext = new WeakReference<Context>(null);
    private volatile static NormalProgressDialog sDialog;


    public NormalProgressDialog(Context context) {
        super(context);
    }

    public NormalProgressDialog(Context context, int theme) {
        super(context, theme);

        mContext = new WeakReference<>(context);
        setOnCancelListener(this);
    }

    @Override
    public void onCancel(DialogInterface dialogInterface) {
        // 点手机返回键等触发Dialog消失，应该取消正在进行的网络请求等
        Context context = mContext.get();
    }

    public static synchronized void showLoading(Context context) {
        showLoading(context, "loading...");
    }

    public static synchronized void showLoading(Context context, CharSequence message) {
        showLoading(context, message, true);
    }

    public static synchronized void showLoading(Context context, CharSequence message, boolean cancelable) {
        if (sDialog != null && !sDialog.isShowing()) {
            sDialog.dismiss();
        }

        if (context == null || !(context instanceof Activity)) {
            return;
        }

        sDialog = new NormalProgressDialog(context);
        sDialog.setMessage(message);
        sDialog.setCancelable(cancelable);

        if (sDialog != null && !sDialog.isShowing() && !((Activity) context).isFinishing()) {
            sDialog.show();
        }
    }

    public static synchronized void dismissLoading() {
        if (sDialog != null && sDialog.isShowing()) {
            sDialog.dismiss();
        }
        sDialog = null;
    }

}
