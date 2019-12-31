package com.example.libingzheng20191231.mvp;

import com.example.libingzheng20191231.base.BaseView;
import com.example.libingzheng20191231.bean.Bean;

public interface Contract {
    interface View extends BaseView{
        void onSuccess(Bean bean);
        void onError(String stg);
    }
    interface Model{
        void getData(String url,ModelCallBack callBack);
        interface ModelCallBack{
            void onSuccess(Bean bean);
            void onError(String stg);
        }
    }
    interface Presenter{
        void getData(String url);
    }
}
