package com.example.systemintegrationproject;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Random;

//public class NotificationHelper extends ContextWrapper {
//
//    private static final String TAG = "NotificationHelper";
//
//    public NotificationHelper(Context base) {
//        super(base);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            createChannels();
//        }
//    }
//
//    private String CHANNEL_NAME = "High priority channel";
//    private String CHANNEL_ID = "com.example.notifications" + CHANNEL_NAME;
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    private void createChannels() {
//        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
//        notificationChannel.enableLights(true);
//        notificationChannel.enableVibration(true);
//        notificationChannel.setDescription("this is the description of the channel.");
//        notificationChannel.setLightColor(Color.RED);
//        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
//        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.createNotificationChannel(notificationChannel);
//    }
//
//    @SuppressLint("MissingPermission")
//    public void sendHighPriorityNotification(String title, String body, Class activityName) {
//
//        Intent intent = new Intent(this, activityName);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 267, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
////                .setContentTitle(title)
////                .setContentText(body)
//                .setSmallIcon(android.R.drawable.ic_dialog_info)
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .setStyle(new NotificationCompat.BigTextStyle().setSummaryText("summary").setBigContentTitle(title).bigText(body))
//                .setContentIntent(pendingIntent)
//                .setAutoCancel(true)
//                .build();
//
//
//        NotificationManagerCompat.from(this).notify(new Random().nextInt(), notification);
//
//
//    }
//
//}

public class NotificationHelper extends ContextWrapper {
    private static final String CHANNEL_NAME = "High priority channel";
    private static final String CHANNEL_ID = "com.example.notifications" + CHANNEL_NAME;

    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannels();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannels() {
        NotificationChannel notificationChannel = new NotificationChannel(
                CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
        notificationChannel.enableLights(true);
        notificationChannel.enableVibration(true);
        notificationChannel.setDescription("High priority notifications.");
        notificationChannel.setLightColor(Color.RED);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null) {
            manager.createNotificationChannel(notificationChannel);
        }
    }

    @SuppressLint("MissingPermission")
    public void sendHighPriorityNotification(String title, String body, Class activityName) {
        Intent intent = new Intent(this, activityName);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 267, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.ic_notifications)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(body))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        NotificationManagerCompat.from(this).notify(new Random().nextInt(), notification);
    }
}

