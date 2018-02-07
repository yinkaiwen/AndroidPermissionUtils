package com.example.kevin.androidpermission.request;

import android.app.Activity;

import com.example.kevin.androidpermission.api.PermissionRequest;
import com.example.kevin.androidpermission.api.RequestFactory;

/**
 * Created by kevin on 2018/2/6.
 * https://github.com/yinkaiwen
 */

public class LowRequestFactory implements RequestFactory {
    @Override
    public PermissionRequest create(Activity activity) {
        return new LowPermissionRequest(activity);
    }
}
