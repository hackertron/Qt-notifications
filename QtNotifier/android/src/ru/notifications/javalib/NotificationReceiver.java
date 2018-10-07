package ru.notifications.javalib;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.view.View;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationReceiver extends BroadcastReceiver {

    public static final String CHANNEL_ID = "420";
    NotificationManager notificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        notificationManager = (NotificationManager) context.getSystemService
                (NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel =
                    new NotificationChannel(CHANNEL_ID,
                            "Default Channel",
                            NotificationManager.IMPORTANCE_DEFAULT);

            notificationManager.createNotificationChannel(notificationChannel);
        }

        simpleNotification(context);
    }

    public void simpleNotification(Context context) {

        // TODO : Modify the content here
        Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(android.R.mipmap.sym_def_app_icon)
                .setContentTitle("Hello There!")
                .setContentText("I've been expecting you!")
                .setAutoCancel(true)
                .build();

        notificationManager.notify(1111111, notification);

    }
}
