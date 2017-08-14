package com.maxoxo.baseproject.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by maxoxo on 2017/7/19.
 */

public class NetUtils {

    /**
     * 检测网络是否连接
     *
     * @param context 上下文
     * @return 结果
     */
    public static boolean isNetConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null) {
                return true;
            }
        }

        return false;
    }

}
