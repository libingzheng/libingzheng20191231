package com.example.libingzheng20191231.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpUtil {
    Handler handler=new Handler();
    private static OkHttpUtil okHttpUtil;
    private OkHttpClient client;
    private OkHttpUtil(){
        client=new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }
    public boolean is(Context context){
        @SuppressLint("ServiceCast") ConnectivityManager connectivityManager=(ConnectivityManager)context.getApplicationContext().getSystemService(Context.ACCESSIBILITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo!=null){
            return activeNetworkInfo.isAvailable();
        }else {
            return false;
        }
    }
    public static OkHttpUtil getInstance() {
        if (okHttpUtil==null){
            synchronized (OkHttpUtil.class){
                if (okHttpUtil==null){
                    okHttpUtil=new OkHttpUtil();
                }
            }
        }
        return okHttpUtil;
    }

    public void doGet(String url,OkHttpCallBack callBack){
        Request request=new Request.Builder()
                .get()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                callBack.onFailure(e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (callBack!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                callBack.onResponse(response.body().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }

    private OkHttpCallBack callBack;

    public void setCallBack(OkHttpCallBack callBack) {
        this.callBack = callBack;
    }

    public interface OkHttpCallBack{
        void onResponse(String e);
        void onFailure(Throwable throwable);
    }
}
