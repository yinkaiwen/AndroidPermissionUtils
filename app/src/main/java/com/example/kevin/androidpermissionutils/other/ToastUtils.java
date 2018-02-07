package com.example.kevin.androidpermissionutils.other;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by kevin on 2018/2/6.
 * https://github.com/yinkaiwen
 */

public class ToastUtils {

    private static Context sContext;

    public static void setContext(Context context) {
        sContext = context;
    }

    public static void toast(String msg) {
        Toast.makeText(sContext, msg, Toast.LENGTH_SHORT).show();
    }
}
