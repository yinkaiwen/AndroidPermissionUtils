package com.example.kevin.androidpermission;

import android.app.Activity;
import android.os.Build;

import com.example.kevin.androidpermission.request.LowRequestFactory;
import com.example.kevin.androidpermission.request.MRequestFactory;
import com.example.kevin.androidpermission.api.PermissionRequest;
import com.example.kevin.androidpermission.api.RequestFactory;

/**
 * Created by kevin on 2018/2/6.
 * https://github.com/yinkaiwen
 */

public class AndroidPermission {

    private static RequestFactory sFactory;

    static {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            sFactory = new MRequestFactory();
        } else {
            sFactory = new LowRequestFactory();
        }
    }

    public static PermissionRequest from(Activity activity) {
        return sFactory.create(activity);
    }


}
