package com.maxoxo.baseproject.utils;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.maxoxo.baseproject.base.MyApplication;

/**
 * .
 * Created by maxoxo on 2017/7/18.
 */

public class ToastUtils {

    private static Toast sToast;

    private static Handler sHandler = new Handler(Looper.getMainLooper());

    public static void shortToast(@NonNull final CharSequence text) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                show(text, Toast.LENGTH_SHORT);
            }
        });
    }

    public static void longToast(@NonNull final CharSequence text) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                show(text, Toast.LENGTH_LONG);
            }
        });
    }

    public static void shortToast(final int resId) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                show(resId, Toast.LENGTH_SHORT);
            }
        });
    }

    public static void longToast(final int resId) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                show(resId, Toast.LENGTH_LONG);
            }
        });
    }

    private static void show(CharSequence text, int duration) {
        cancel();

        sToast = Toast.makeText(MyApplication.getContext(), text, duration);
        sToast.show();
    }

    private static void show(int resId, int duration) {
        cancel();

        sToast = Toast.makeText(MyApplication.getContext(), resId, duration);
        sToast.show();
    }

    /**
     * 取消吐司显示
     */
    public static void cancel() {
        if (sToast != null) {
            sToast.cancel();
            sToast = null;
        }
    }

}
