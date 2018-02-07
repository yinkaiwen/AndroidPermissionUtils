package com.example.kevin.androidpermission.implementsApi;

import com.example.kevin.androidpermission.DangerousPermissions;
import com.example.kevin.androidpermission.api.DialogMsg;

/**
 * Created by kevin on 2018/2/6.
 * https://github.com/yinkaiwen
 */

public class ChineseDialogMsg extends DialogMsg{

    static {
        map.put(DangerousPermissions.READ_CALENDAR,"-读取日历");
        map.put(DangerousPermissions.WRITE_CALENDAR,"-写入日历");

        map.put(DangerousPermissions.CAMERA,"-拍摄照片和录制视频");

        map.put(DangerousPermissions.READ_CONTACTS,"-读取联系人");
        map.put(DangerousPermissions.WRITE_CONTACTS,"-写入联系人");
        map.put(DangerousPermissions.GET_ACCOUNTS,"-访问GMail账户列表");

        map.put(DangerousPermissions.ACCESS_FINE_LOCATION,"-访问精良位置");
        map.put(DangerousPermissions.ACCESS_COARSE_LOCATION,"-获取错略位置");

        map.put(DangerousPermissions.RECORD_AUDIO,"-录制音频");

        map.put(DangerousPermissions.READ_PHONE_STATE,"-访问电话状态");
        map.put(DangerousPermissions.CALL_PHONE,"-拨打电话");
        map.put(DangerousPermissions.READ_CALL_LOG,"-读取通话记录");
        map.put(DangerousPermissions.WRITE_CALL_LOG,"-写入通话记录");
        map.put(DangerousPermissions.ADD_VOICEMAIL,"-添加系统中的语音邮件");
        map.put(DangerousPermissions.USE_SIP,"-使用SIP视频服务");
        map.put(DangerousPermissions.PROCESS_OUTGOING_CALLS,"-监视、修改、忽略拨出的电话");

        map.put(DangerousPermissions.BODY_SENSORS,"-访问生命体征相关的传感器数据");

        map.put(DangerousPermissions.SEND_SMS,"-发送短消息");
        map.put(DangerousPermissions.RECEIVE_SMS,"-监控收到的短信");
        map.put(DangerousPermissions.READ_SMS,"-读取手机短消息");
        map.put(DangerousPermissions.RECEIVE_WAP_PUSH,"-监测接受的WAP-PUSH消息");
        map.put(DangerousPermissions.RECEIVE_MMS,"-监控收到的彩信");

        map.put(DangerousPermissions.READ_EXTERNAL_STORAGE,"-读外部存储");
        map.put(DangerousPermissions.WRITE_EXTERNAL_STORAGE,"-写入外部存储");
    }


}
