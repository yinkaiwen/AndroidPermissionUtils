package com.example.kevin.androidpermission;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import com.example.kevin.androidpermission.api.DialogMsg;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kevin on 2018/2/6.
 * https://github.com/yinkaiwen
 */

public class PermissionActivity extends Activity {
    private static final String PERMISSION = PermissionActivity.class.getSimpleName() + "Permission";
    private final int REQUEST_CODE = (int) (Math.random() * 10000);
    private static final String LOW_SDK_PERMISSIONS = "LOW_SDK_PERMISSIONS_SHAREDPREFERENCES";
    public static final String UID = "serialVersionUID";
    private static AndroidPermissionLister mLister;

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
                    //sdk >= 23;
                    checkPermissions(permissions);
                } else {
                    // sdk <= 22;
                    lowSdkCheckPermissions(permissions);
                }
            } else {
                finish();
            }
        } else {
            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (mLister != null) {
            mLister.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        finish();
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

    private void lowSdkCheckPermissions(String... permissions) {
        initLowSdkPermissions();
        String[] lowDeniedPermissions = getLowDeniedPermissions(permissions);
        if (lowDeniedPermissions.length == 0) {
            int[] grantResults = getGrantResults(PackageManager.PERMISSION_GRANTED, permissions.length);
            onRequestPermissionsResult(REQUEST_CODE, permissions, grantResults);
        } else {
            showSelfDialog(lowDeniedPermissions);
        }
    }

    private void initLowSdkPermissions() {
        SharedPreferences sp = getSp();
        Map<String, ?> all = sp.getAll();
        if (all.size() == 0) {
            SharedPreferences.Editor edit = sp.edit();
            Class<DangerousPermissions> cls = DangerousPermissions.class;
            try {
                DangerousPermissions dangerousPermissions = cls.newInstance();
                Field[] declaredFields = cls.getFields();
                for (Field field : declaredFields) {
                    if (field.isSynthetic())
                        continue;
                    if (UID.equals(field.getName()))
                        continue;
                    String s = (String) field.get(dangerousPermissions);
                    edit.putInt(s, PackageManager.PERMISSION_DENIED);
                }
                edit.apply();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private String[] getLowDeniedPermissions(String... permissions) {
        List<String> deniedPermissions = new ArrayList<>();
        SharedPreferences sp = getSp();
        for (String p : permissions) {
            if (sp.getInt(p, PackageManager.PERMISSION_DENIED) == PackageManager.PERMISSION_DENIED) {
                deniedPermissions.add(p);
            }
        }
        String[] rs = new String[deniedPermissions.size()];
        rs = deniedPermissions.toArray(rs);
        return rs;
    }

    private void showSelfDialog(final String[] permissions) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(getMsg(permissions))
                .setPositiveButton(R.string.positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setPermissionsGrant(permissions);
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

    private void setPermissionsGrant(String... permissionsGrant){
        SharedPreferences sp = getSp();
        SharedPreferences.Editor edit = sp.edit();
        for(String p : permissionsGrant){
            edit.putInt(p,PackageManager.PERMISSION_GRANTED);
        }
        edit.apply();
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

    private int[] getGrantResults(int result, int length) {
        int[] grantResults = new int[length];
        for (int i = 0; i < length; i++) {
            grantResults[i] = result;
        }
        return grantResults;
    }

    private SharedPreferences getSp() {
        return getSharedPreferences(LOW_SDK_PERMISSIONS, MODE_PRIVATE);
    }

    public interface AndroidPermissionLister {
        void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);
    }

    @Override
    protected void onDestroy() {
        mLister = null;
        super.onDestroy();
    }
}
