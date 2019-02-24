package electronicwallet.lyhoangvinh.com.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

import electronicwallet.lyhoangvinh.com.R;


public class NotificationManagerHelper {

    private static final int LED_ON_INTERVAL  = 500;
    private static final int LED_OFF_INTERVAL = 3000;

    public static final int DEFAULT_NOTIFY_ID           = 999;
    public static final String NOTIFICATION_CHANNEL_ID  = "my_channel_id";

    public static final String COLLAPSE_KEY_NULL    = "COLLAPSE_KEY_NULL";

    public static void show(Context context, long id, Notification notification) {
        try {
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (manager != null) {
                manager.notify((int) id, notification);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cancel(Context context, long id) {
        try {
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (manager != null) {
                manager.cancel((int) id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cancelGroup(Context context, String roomId) {
        try {
            int notificationId = TextUtils.isEmpty(roomId) ? DEFAULT_NOTIFY_ID : roomId.hashCode();
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (manager != null) {
                manager.cancel(notificationId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cancelAll(Context context) {
        try {
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (manager != null) {
                manager.cancelAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getUniqueId() {
        return (StringHelper.getUniqueId()).hashCode();
    }

    public static int getUniqueId(String target) {
        if (target == null || target.length() == 0) {
            return DEFAULT_NOTIFY_ID;
        }
        return target.hashCode();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void initNotificationChannel(@NonNull Context context) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        String name = context.getString(R.string.app_name) + " " + NOTIFICATION_CHANNEL_ID;
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel mChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, name, importance);
        if (manager != null) {
            manager.createNotificationChannel(mChannel);
        }
    }
}
