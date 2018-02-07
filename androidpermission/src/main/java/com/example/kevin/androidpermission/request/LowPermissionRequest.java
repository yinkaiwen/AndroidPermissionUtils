package com.example.kevin.androidpermission.request;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.example.kevin.androidpermission.PermissionActivity;
import com.example.kevin.androidpermission.api.DeniedCallBack;
import com.example.kevin.androidpermission.api.GrantedCallBack;
import com.example.kevin.androidpermission.api.PermissionRequest;

/**
 * Created by kevin on 2018/2/6.
 * https://github.com/yinkaiwen
 */

public class LowPermissionRequest extends AbsPermissionRequest {

    private Activity mActivity;
    private String[] mPermissions;

    LowPermissionRequest(Activity activity) {
        mActivity = activity;
    }

    @Override
    public PermissionRequest permissions(String... permissions) {
        mPermissions = permissions;
        return this;
    }

    @Override
    public void start() {
        PermissionActivity.requestPermissionsByPermissionActivity(mActivity, mPermissions, this);
    }


}
