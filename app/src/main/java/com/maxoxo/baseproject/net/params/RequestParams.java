package com.maxoxo.baseproject.net.params;

import java.io.File;
import java.util.LinkedHashMap;

/**
 * Created by xielipeng on 2017/1/18.
 */
public class RequestParams {

    private LinkedHashMap<String, String> mParams;
    private LinkedHashMap<String, File> mFileParams;

    public RequestParams() {
        mParams = new LinkedHashMap<>();
        mFileParams = new LinkedHashMap<>();
    }

    public RequestParams(String key, Object value) {
        this();
        add(key, value);
    }

    public RequestParams add(String key, Object value) {
        if (key == null) return this;
        if (value == null) mParams.put(key, null);
        else if (value instanceof File) mFileParams.put(key, (File) value);
        else mParams.put(key, value.toString());
        return this;
    }

    public LinkedHashMap<String, String> get() {
        return mParams;
    }

    public LinkedHashMap<String, File> files() {
        return mFileParams;
    }
}
