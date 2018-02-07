package com.example.kevin.androidpermission.api;

import com.example.kevin.androidpermission.PermissionActivity;

/**
 * Created by kevin on 2018/2/6.
 * https://github.com/yinkaiwen
 */

public interface PermissionRequest extends PermissionActivity.AndroidPermissionLister{

    PermissionRequest permissions(String... permissions);

    void start();

    PermissionRequest setGrantedCallBack(GrantedCallBack grantedCallBack);

    PermissionRequest setDeniedCallBack(DeniedCallBack deniedCallBack);

}
