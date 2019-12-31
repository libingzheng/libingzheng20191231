package com.example.libingzheng20191231.mvp;

import com.example.libingzheng20191231.utils.OkHttpUtil;

public class Model implements Contract.Model{
    @Override
    public void getData(String url, ModelCallBack callBack) {
       OkHttpUtil.getInstance().doGet(url, new OkHttpUtil.OkHttpCallBack() {
           @Override
           public void onResponse(String e) {
               callBack.onError(e);
           }

           @Override
           public void onFailure(Throwable throwable) {

           }
       });
    }
}
