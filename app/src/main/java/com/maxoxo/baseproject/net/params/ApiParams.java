package com.maxoxo.baseproject.net.params;

/**
 * Created by xielipeng on 2017/1/18.
 */

public class ApiParams {
    private static ApiParams mApiParams;

    public static ApiParams getInstance() {
        if (mApiParams == null) {
            mApiParams = new ApiParams();
        }
        return mApiParams;
    }

    public RequestParams getParams() {
        RequestParams params = new RequestParams();
//        params.add("token", SPUtils.get(MyApplication.getInstance(), "token", ""));
        return params;
    }
}
