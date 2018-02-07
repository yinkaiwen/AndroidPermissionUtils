package com.example.kevin.androidpermission.implementsApi;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by kevin on 2018/2/6.
 * https://github.com/yinkaiwen
 */
@RequiresApi(api = Build.VERSION_CODES.M)
class MPermissionRequest extends AbsPermissionRequest {

    MPermissionRequest(Activity activity) {
        mActivity = activity;
    }

}
