package com.example.libingzheng20191231.mvp;

import com.example.libingzheng20191231.base.BasePresenter;
import com.example.libingzheng20191231.bean.Bean;

public class Presenter extends BasePresenter<Contract.View> implements Contract.Presenter {


    private Model model;

    @Override
    protected void initModel() {
        model = new Model();
    }

    @Override
    public void getData(String url) {
        model.getData(url, new Contract.Model.ModelCallBack() {
            @Override
            public void onSuccess(Bean bean) {
                getView().onSuccess(bean);
            }

            @Override
            public void onError(String stg) {
                getView().onError(stg);
            }
        });
    }
}
