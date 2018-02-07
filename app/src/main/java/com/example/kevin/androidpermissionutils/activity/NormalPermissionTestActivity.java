package com.example.kevin.androidpermissionutils.activity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.example.kevin.androidpermission.DangerousPermissions;
import com.example.kevin.androidpermissionutils.R;
import com.example.kevin.androidpermissionutils.other.ToastUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 2018/2/6.
 * https://github.com/yinkaiwen
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public class NormalPermissionTestActivity extends Activity implements View.OnClickListener {

    private static final int MY_PERMISSIONS_REQUEST_READ_CALENDAR = 100;
    private static final int CONTACT_GROUP = 101;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_permission);
        bindView();
    }

    private void bindView() {
        findViewById(R.id.btn_check_calendar).setOnClickListener(this);
        findViewById(R.id.btn_request_calendar).setOnClickListener(this);
        findViewById(R.id.btn_request_contact_group).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_check_calendar:
                checkPermission();
                break;
            case R.id.btn_request_calendar:
                requestCalendar();
                break;
            case R.id.btn_request_contact_group:
                requestContactGroup();
                break;
        }
    }

    private void checkPermission() {
        int i = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR);
        switch (i) {
            case PackageManager.PERMISSION_DENIED:
                ToastUtils.toast("denied");
                break;
            case PackageManager.PERMISSION_GRANTED:
                ToastUtils.toast("granted");
                break;
        }
    }

    private void requestCalendar() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CALENDAR},
                        MY_PERMISSIONS_REQUEST_READ_CALENDAR);
            }
        }
    }

    int index = 0;

    private void requestContactGroup() {
/*        Class<DangerousPermissions> cls = DangerousPermissions.class;
        try {
            DangerousPermissions dangerousPermissions = cls.newInstance();
            Field[] declaredFields = cls.getFields();
            List<String> list= new ArrayList<>();
            for(Field field : declaredFields){
                if (field.isSynthetic())
                    continue;
                if (UID.equals(field.getName()))
                    continue;
                String s = (String) field.get(dangerousPermissions);
                list.add(s);
            }
            String[] a = new String[list.size()];
            a = list.toArray(a);
            requestPermissions(a,CONTACT_GROUP);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }*/

        requestPermissions(new String[]{"android.permission.READ_CONTACTS", "android.permission.WRITE_CONTACTS", "android.permission.GET_ACCOUNTS"}, CONTACT_GROUP);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        String msg = getMsg(permissions, grantResults);
        if (msg != null) {
            ToastUtils.toast(msg);
        }
    }

    private String getMsg(String[] permissions, int[] grantResults) {
        if (permissions == null || permissions.length == 0)
            return null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < permissions.length; i++) {
            sb.append(String.format("%s,%s\n", permissions[i],
                    grantResults[i] == PackageManager.PERMISSION_GRANTED ? "granted" : "denied"));
        }
        return sb.toString();
    }
}
