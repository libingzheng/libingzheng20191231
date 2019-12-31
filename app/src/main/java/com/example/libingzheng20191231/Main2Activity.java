package com.example.libingzheng20191231;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.wx)
    Button wx;
    @BindView(R.id.qq)
    Button qq;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bind = ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @OnClick(R.id.img)
    public void onViewClicked() {
        Bitmap bitmap = CodeUtils.createImage("李秉正", 400, 4000, null);
        img.setImageBitmap(bitmap);
    }

    @OnClick({R.id.wx, R.id.qq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wx:
                EventBus.getDefault().post("微信");
                break;
            case R.id.qq:
                EventBus.getDefault().post("QQ");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        bind.unbind();
    }
}
