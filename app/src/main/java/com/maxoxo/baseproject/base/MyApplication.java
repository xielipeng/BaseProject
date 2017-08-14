package com.maxoxo.baseproject.base;

import android.app.Application;
import android.content.Context;

import com.maxoxo.baseproject.BuildConfig;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * .
 * Created by maxoxo on 2017/7/16.
 */

public class MyApplication extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();

        initLogger();
    }

    public static Context getContext() {
        return sContext;
    }

    private void initLogger() {
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }
}
