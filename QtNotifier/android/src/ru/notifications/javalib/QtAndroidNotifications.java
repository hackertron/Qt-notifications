package ru.notifications.javalib;

// Qt

import org.qtproject.qt5.android.QtNative;

// android
import android.app.AlarmManager;
import android.content.Intent;
import android.content.Context;
import android.app.PendingIntent;
import android.app.Notification;
import android.app.NotificationManager;

// java
import java.lang.String;

class QtAndroidNotifications {


    //TODO : Send the EPOCH time in milliseconds to this method at which the alarm is to be scheduled
    public static void scheduleNotification(long time) {
        Context context = QtNative.activity();
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, NotificationReceiver.class);

        PendingIntent pi = PendingIntent.getBroadcast(context, 12345, intent, PendingIntent
                .FLAG_UPDATE_CURRENT);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, pi);

    }

    public static void show(String title, String caption, int id) {
        System.out.println("show");

        Context context = QtNative.activity();

        NotificationManager notificationManager = getManager();
        Notification.Builder builder =
                new Notification.Builder(context)
                        .setSmallIcon(android.R.drawable.ic_delete)
                        .setContentTitle(title)
                        .setContentText(caption)
                        .setAutoCancel(true);

        String packageName = context.getApplicationContext().getPackageName();
        Intent resultIntent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        context, 0,
                        resultIntent, PendingIntent.FLAG_UPDATE_CURRENT
                );

        builder.setContentIntent(resultPendingIntent);
        notificationManager.notify(id, builder.build());
    }

    public static void hide(int id) {
        getManager().cancel(id);
    }

    public static void hideAll() {
        getManager().cancelAll();
    }

    private static NotificationManager getManager() {
        Context context = QtNative.activity();
        return (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }
}
