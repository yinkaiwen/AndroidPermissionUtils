package com.example.kevin.androidpermissionutils.activity;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.kevin.androidpermission.AndroidPermission;
import com.example.kevin.androidpermission.api.DeniedCallBack;
import com.example.kevin.androidpermission.api.GrantedCallBack;
import com.example.kevin.androidpermissionutils.R;
import com.example.kevin.androidpermissionutils.other.ToastUtils;

/**
 * Created by kevin on 2018/2/6.
 * https://github.com/yinkaiwen
 */

public class UtilsTestActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utilstest);
        bindView();
    }

    private void bindView() {
        findViewById(R.id.btn_test1).setOnClickListener(this);
        findViewById(R.id.btn_test2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test1:
                requestPermission(Manifest.permission.READ_CALENDAR);
                break;
            case R.id.btn_test2:
                requestPermission("android.permission.READ_CONTACTS", "android.permission.WRITE_CONTACTS", "android.permission.GET_ACCOUNTS");
                break;
        }
    }

    private void requestPermission(String... permissions) {
        AndroidPermission.from(this)
                .permissions(permissions)
                .setDeniedCallBack(new DeniedCallBack() {
                    @Override
                    public void denied() {
                        afterDenied();
                    }
                })
                .setGrantedCallBack(new GrantedCallBack() {
                    @Override
                    public void granted() {
                        afterSuccess();
                    }
                })
                .start();
    }

    private void afterSuccess() {
        ToastUtils.toast("success");
    }

    private void afterDenied() {
        ToastUtils.toast("denied");
    }
}
