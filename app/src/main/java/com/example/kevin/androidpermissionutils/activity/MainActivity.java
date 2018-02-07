package com.example.kevin.androidpermissionutils.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.kevin.androidpermissionutils.R;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView();
    }

    private void bindView() {
        findViewById(R.id.btn_normal_permission).setOnClickListener(this);
        findViewById(R.id.test_utils).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_normal_permission:
                startActivity(new Intent(this, NormalPermissionTestActivity.class));
                break;
            case R.id.test_utils:
                startActivity(new Intent(this, UtilsTestActivity.class));
                break;
        }
    }
}
