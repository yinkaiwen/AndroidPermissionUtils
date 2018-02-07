package com.example.kevin.androidpermission.request;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import com.example.kevin.androidpermission.PermissionActivity;
import com.example.kevin.androidpermission.api.DeniedCallBack;
import com.example.kevin.androidpermission.api.GrantedCallBack;
import com.example.kevin.androidpermission.api.PermissionRequest;

/**
 * Created by kevin on 2018/2/6.
 * https://github.com/yinkaiwen
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public class MPermissionRequest extends AbsPermissionRequest {

    private Activity mActivity;
    private String[] mPermissions;

    MPermissionRequest(Activity activity) {
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
