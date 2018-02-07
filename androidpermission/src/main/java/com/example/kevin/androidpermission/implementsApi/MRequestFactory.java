package com.example.kevin.androidpermission.implementsApi;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.kevin.androidpermission.api.PermissionRequest;
import com.example.kevin.androidpermission.api.RequestFactory;

/**
 * Created by kevin on 2018/2/6.
 * https://github.com/yinkaiwen
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public class MRequestFactory implements RequestFactory {

    @Override
    public PermissionRequest create(Activity activity) {
        return new MPermissionRequest(activity);
    }

}
