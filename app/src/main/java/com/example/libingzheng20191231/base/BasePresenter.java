package com.example.libingzheng20191231.base;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends BaseView> {

    private WeakReference<V> weakReference;

    public BasePresenter(){
        initModel();
    }
    public void attachView(V v){
        weakReference = new WeakReference<>(v);
    }
    public void detachView(){
        if (weakReference!=null){
            weakReference.clear();
            weakReference=null;
        }
    }
    protected abstract void initModel();
    public V getView(){
        V v = weakReference.get();
        return v;
    }
}
