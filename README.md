# AndroidPermissionUtils
This lib can apply application lower than 23.(Just show dialog,seems like SDK apply permissions)

This lib is easy,you can use such code.
```
AndroidPermission.from(this)
        .permissions(permissions)
        .setDeniedCallBack(new DeniedCallBack() {
            @Override
            public void denied() {
                afterDenied();
            }
        })
        .setGrantedCallBack(new GrantedCallBack() {
            @Override
            public void granted() {
                afterSuccess();
            }
        })
        .start();
```
 This gif is showing the demo that SDK lower than 23.</br>
![image](https://github.com/yinkaiwen/AndroidPermissionUtils/raw/master/test.gif)
