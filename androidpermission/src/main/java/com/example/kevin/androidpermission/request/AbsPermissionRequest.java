package com.example.kevin.androidpermission.request;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import com.example.kevin.androidpermission.PermissionActivity;
import com.example.kevin.androidpermission.api.DeniedCallBack;
import com.example.kevin.androidpermission.api.GrantedCallBack;
import com.example.kevin.androidpermission.api.PermissionRequest;

/**
 * Created by kevin on 2018/2/6.
 * https://github.com/yinkaiwen
 */

public abstract class AbsPermissionRequest implements PermissionRequest {
    private DeniedCallBack mDeniedCallBack;
    private GrantedCallBack mGrantedCallBack;
    private String[] mPermissions;
    Activity mActivity;

    @Override
    public PermissionRequest permissions(String... permissions) {
        mPermissions = permissions;
        return this;
    }

    @Override
    public PermissionRequest setDeniedCallBack(DeniedCallBack deniedCallBack) {
        mDeniedCallBack = deniedCallBack;
        return this;
    }

    @Override
    public PermissionRequest setGrantedCallBack(GrantedCallBack grantedCallBack) {
        mGrantedCallBack = grantedCallBack;
        return this;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0) {
            int rs = grantResults[0];
            if (rs == PackageManager.PERMISSION_GRANTED) {
                if (mGrantedCallBack != null) {
                    mGrantedCallBack.granted();
                }
            } else if (rs == PackageManager.PERMISSION_DENIED) {
                if (mDeniedCallBack != null) {
                    mDeniedCallBack.denied();
                }
            }
        }
    }

    @Override
    public void start() {
        if(mPermissions == null){
            throw new IllegalArgumentException("You should use permissions() method before use this.");
        }
        PermissionActivity.requestPermissionsByPermissionActivity(mActivity, mPermissions, this);
    }
}
