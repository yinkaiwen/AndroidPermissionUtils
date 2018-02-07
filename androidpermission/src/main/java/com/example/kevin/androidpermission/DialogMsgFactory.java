package com.example.kevin.androidpermission;

import android.content.res.Resources;

import com.example.kevin.androidpermission.api.DialogMsg;
import com.example.kevin.androidpermission.implementsApi.ChineseDialogMsg;

/**
 * Created by kevin on 2018/2/6.
 * https://github.com/yinkaiwen
 */

public class DialogMsgFactory {
   public static DialogMsg createDialogMsg(Resources resources){
        DialogMsg dialogMsg = null;
        String language = resources.getConfiguration().locale.getLanguage();
        if (language.endsWith("zh")) {
            dialogMsg = new ChineseDialogMsg();
        }else{
            //lazy,can not implements this.
        }

        if(dialogMsg == null)
            dialogMsg = new ChineseDialogMsg();

        resources = null;
        return dialogMsg;
    }
}
