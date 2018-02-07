package com.example.kevin.androidpermission.implementsApi;

import android.app.Activity;

/**
 * Created by kevin on 2018/2/6.
 * https://github.com/yinkaiwen
 */

class LowPermissionRequest extends AbsPermissionRequest {

    LowPermissionRequest(Activity activity) {
        mActivity = activity;
    }

}
