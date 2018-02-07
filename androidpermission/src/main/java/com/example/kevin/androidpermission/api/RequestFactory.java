package com.example.kevin.androidpermission.api;

import android.app.Activity;

/**
 * Created by kevin on 2018/2/6.
 * https://github.com/yinkaiwen
 */

public interface RequestFactory {
    PermissionRequest create(Activity activity);
}
