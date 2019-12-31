package com.example.libingzheng20191231;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libingzheng20191231.adapter.MyAdapter;
import com.example.libingzheng20191231.base.BaseActivity;
import com.example.libingzheng20191231.bean.Bean;
import com.example.libingzheng20191231.mvp.Contract;
import com.example.libingzheng20191231.mvp.Presenter;
import com.example.libingzheng20191231.utils.OkHttpUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<Presenter> implements Contract.View {
    @BindView(R.id.fx)
    TextView fx;
    private List<Bean> list;

    public MainActivity(List<Bean> list) {
        this.list = list;
    }

    @BindView(R.id.rv)
    RecyclerView rv;

    @Override
    protected void initData() {
        presenter.getData("http://blog.zhaoliang5156.cn/api/news/ranking.json");
    }

    @Override
    protected void initView() {
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onSuccess(Bean bean) {
        boolean b = OkHttpUtil.getInstance().is(this);
        if (b){
            Toast.makeText(this, "有网", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "没网", Toast.LENGTH_SHORT).show();
        }
        MyAdapter myAdapter = new MyAdapter(this, list);
        rv.setAdapter(myAdapter);
    }

    @Override
    public void onError(String stg) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.fx)
    public void onViewClicked() {
        Intent intent=new Intent(MainActivity.this,Main2Activity.class);
        startActivity(intent);
    }
}
