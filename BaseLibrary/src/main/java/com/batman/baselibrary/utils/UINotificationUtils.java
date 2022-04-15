package com.batman.baselibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationManagerCompat;

public class UINotificationUtils {

    public static void a(Activity var0) {
        Intent var1 = new Intent();

        Exception var10000;
        label64:
        {
            int var2;
            boolean var10001;
            try {
                var2 = Build.VERSION.SDK_INT;
            } catch (Exception var9) {
                var10000 = var9;
                var10001 = false;
                break label64;
            }

            if (var2 >= 26) {
                try {
                    var1.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                    var1.putExtra("android.provider.extra.APP_PACKAGE", var0.getPackageName());
                } catch (Exception var6) {
                    var10000 = var6;
                    var10001 = false;
                    break label64;
                }
            } else {
                label66:
                {
                    try {
                        if (Build.VERSION.SDK_INT >= 21) {
                            var1.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                            var1.putExtra("app_package", var0.getPackageName());
                            var1.putExtra("app_uid", var0.getApplicationInfo().uid);
                            break label66;
                        }
                    } catch (Exception var8) {
                        var10000 = var8;
                        var10001 = false;
                        break label64;
                    }

                    try {
                        if (Build.VERSION.SDK_INT == 19) {
                            var1.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                            var1.addCategory("android.intent.category.DEFAULT");
                            StringBuilder var3 = new StringBuilder();
                            var3.append("package:");
                            var3.append(var0.getPackageName());
                            var1.setData(Uri.parse(var3.toString()));
                            break label66;
                        }
                    } catch (Exception var7) {
                        var10000 = var7;
                        var10001 = false;
                        break label64;
                    }

                    try {
                        var1.setAction("android.settings.SETTINGS");
                    } catch (Exception var5) {
                        var10000 = var5;
                        var10001 = false;
                        break label64;
                    }
                }
            }

            try {
                var0.startActivityForResult(var1, 1004);
                return;
            } catch (Exception var4) {
                var10000 = var4;
                var10001 = false;
            }
        }

        Exception var10 = var10000;
        Intent var11 = new Intent();
        var11.setAction("android.settings.SETTINGS");
        var0.startActivityForResult(var11, 1004);
        var10.printStackTrace();
    }

    /**
     * 判断是否打开通知栏
     *
     * @param context
     * @return
     */
    public static boolean checkNotifactionIsOpen(Context context) {
        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        boolean notificationsEnabled = manager.areNotificationsEnabled();
        return notificationsEnabled;

    }
}
