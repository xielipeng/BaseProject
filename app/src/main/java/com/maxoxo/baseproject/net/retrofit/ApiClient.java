package com.maxoxo.baseproject.net.retrofit;

import com.maxoxo.baseproject.BuildConfig;
import com.maxoxo.baseproject.base.MyApplication;
import com.maxoxo.baseproject.utils.NetUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by maxoxo on 2017/7/19.
 */

public class ApiClient {

    private static Retrofit sRetrofit = null;

    public static Retrofit retrofit() {
        if (sRetrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            // Log信息拦截器
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(loggingInterceptor);
            }

            // 设置头
            Interceptor interceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request originalRequest = chain.request();
                    Request.Builder requestBuilder = originalRequest.newBuilder()
                            .header("token", "tokenValue")
                            .header("Cookie", "cookieValue")
                            .method(originalRequest.method(), originalRequest.body());

                    Request request = requestBuilder.build();

                    return chain.proceed(request);
                }
            };
            builder.addInterceptor(interceptor);

            //设置超时和重连
            builder.connectTimeout(10, TimeUnit.SECONDS);
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.writeTimeout(20, TimeUnit.SECONDS);
            //错误重连
            builder.retryOnConnectionFailure(true);

            //设置缓存
            File cacheFile = new File(MyApplication.getContext().getExternalCacheDir(), "okHttpCache");
            Cache cache = new Cache(cacheFile, 10 * 1024 * 1024);
            Interceptor cacheInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    //无网络时，显示缓存数据
                    Request request = chain.request();
                    if (!NetUtils.isNetConnected(MyApplication.getContext())) {
                        request = request.newBuilder()
                                .cacheControl(CacheControl.FORCE_CACHE)
                                .build();
                    }
                    Response response = chain.proceed(request);
                    if (NetUtils.isNetConnected(MyApplication.getContext())) {
                        int maxAge = 60;
                        // 有网络时 设置缓存超时时间60秒
                        response.newBuilder()
                                .header("Cache-Control", "public, max-age=" + maxAge)
                                .removeHeader("pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                                .build();
                    } else {
                        // 无网络时，设置超时为4周
                        int maxStale = 60 * 60 * 24 * 28;
                        response.newBuilder()
                                .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                                .removeHeader("nyn")
                                .build();
                    }
                    return response;
                }
            };
            builder.cache(cache).addInterceptor(cacheInterceptor);

            OkHttpClient okHttpClient = builder.build();
            sRetrofit = new Retrofit.Builder()
                    .baseUrl("http://baidu.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }

        return sRetrofit;
    }
}
