package com.example.kevin.androidpermissionutils.other;

import android.app.Application;

/**
 * Created by kevin on 2018/2/6.
 * https://github.com/yinkaiwen
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtils.setContext(this);
    }
}
