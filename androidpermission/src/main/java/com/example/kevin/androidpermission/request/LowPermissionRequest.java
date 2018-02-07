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

class LowPermissionRequest extends AbsPermissionRequest {

    LowPermissionRequest(Activity activity) {
        mActivity = activity;
    }

}
