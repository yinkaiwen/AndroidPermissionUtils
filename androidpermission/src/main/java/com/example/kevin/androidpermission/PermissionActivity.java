package com.example.kevin.androidpermission;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import com.example.kevin.androidpermission.api.DialogMsg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kevin on 2018/2/6.
 * https://github.com/yinkaiwen
 */

public class PermissionActivity extends Activity {
    private static final String PERMISSION = PermissionActivity.class.getSimpleName() + "Permission";
    private final int REQUEST_CODE = (int) (Math.random() * 10000);

    public static void requestPermissionsByPermissionActivity(Activity activity, String[] permissions, AndroidPermissionLister lister) {
        mLister = lister;
        Intent intent = new Intent(activity, PermissionActivity.class);
        intent.putExtra(PERMISSION, permissions);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (intent != null) {
            String[] permissions = intent.getStringArrayExtra(PERMISSION);
            if (permissions != null && permissions.length != 0) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    checkPermissions(permissions);
                } else {
                    // sdk <= 22;
                    showSelfDialog(permissions);
                }
            } else {
                finish();
            }
        } else {
            finish();
        }
    }

    private void showSelfDialog(final String[] permissions) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(getMsg(permissions))
                .setPositiveButton(R.string.positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        onRequestPermissionsResult(REQUEST_CODE, permissions, getGrantResults(PackageManager.PERMISSION_GRANTED, permissions.length));
                    }
                })
                .setNegativeButton(R.string.negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        onRequestPermissionsResult(REQUEST_CODE, permissions, getGrantResults(PackageManager.PERMISSION_DENIED, permissions.length));
                    }
                })
                .create()
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (mLister != null) {
            mLister.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        finish();
    }

    private static AndroidPermissionLister mLister;

    public interface AndroidPermissionLister {
        void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);
    }

    private String getMsg(String[] permissions) {
        StringBuilder sb = new StringBuilder();
        Resources resources = getResources();
        sb.append(resources.getString(R.string.msg1))
                .append(resources.getString(R.string.app_name))
                .append(resources.getString(R.string.msg2))
                .append("\n");
        DialogMsg dialogMsg = DialogMsgFactory.createDialogMsg(getResources());
        HashMap<String, String> map = dialogMsg.map;
        for (String s : permissions) {
            sb.append(" ").append(map.get(s)).append("\n");
        }
        return sb.toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkPermissions(String... permissions) {
        String[] deniedPermissions = getDeniedPermissions(permissions);
        if (deniedPermissions.length == 0) {
            int[] grantResults = getGrantResults(PackageManager.PERMISSION_GRANTED, permissions.length);
            onRequestPermissionsResult(REQUEST_CODE, permissions, grantResults);
        } else {
            requestPermissions(deniedPermissions, REQUEST_CODE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private String[] getDeniedPermissions(String... permissions) {
        List<String> deniedPermissionList = new ArrayList<>();
        for (String p : permissions) {
            if (checkSelfPermission(p) == PackageManager.PERMISSION_DENIED) {
                deniedPermissionList.add(p);
            }
        }
        String[] rs = new String[deniedPermissionList.size()];
        rs = deniedPermissionList.toArray(rs);
        return rs;
    }

    private int[] getGrantResults(int result, int length) {
        int[] grantResults = new int[length];
        for (int i = 0; i < length; i++) {
            grantResults[i] = result;
        }
        return grantResults;
    }

    @Override
    protected void onDestroy() {
        mLister = null;
        super.onDestroy();
    }
}
