package com.example.systemintegrationproject;

//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.location.Location;
//
//import android.os.Looper;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.google.android.gms.location.Geofence;
//import com.google.android.gms.location.GeofencingEvent;
//
//import java.util.List;
//import java.util.logging.Handler;
//
//public class GeofenceBroadcastReceiver extends BroadcastReceiver {
//
//    private static final String TAG = "GeofenceBroadcastReceiv";
//    private static final String PREF_NAME = "GeofencePrefs";
//    private static final String KEY_NOTIFICATION_SENT = "notificationSent";
//
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        // TODO: This method is called when the BroadcastReceiver is receiving
//        // an Intent broadcast.
////        Log.d("GeofenceReceiver", "Geofence triggered!");
////        Toast.makeText(context, "Geofence triggered...", Toast.LENGTH_SHORT).show();
//
//        NotificationHelper notificationHelper = new NotificationHelper(context);
//
//        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
//
//        if (geofencingEvent.hasError()) {
//            Log.d(TAG, "onReceive: Error receiving geofence event...");
//            return;
//        }
//
//        List<Geofence> geofenceList = geofencingEvent.getTriggeringGeofences();
//        for (Geofence geofence: geofenceList) {
//            Log.d(TAG, "onReceive: " + geofence.getRequestId());
//        }
////        Location location = geofencingEvent.getTriggeringLocation();
//        int transitionType = geofencingEvent.getGeofenceTransition();
//
////        switch (transitionType) {
////            case Geofence.GEOFENCE_TRANSITION_ENTER:
////                Toast.makeText(context, "Entering SafeCheck...", Toast.LENGTH_SHORT).show();
//////                notificationHelper.sendHighPriorityNotification("Student Entering SafeCheck", "", MapsActivity.class);
////                break;
////            case Geofence.GEOFENCE_TRANSITION_DWELL:
////                Toast.makeText(context, "Inside of SafeCheck...", Toast.LENGTH_SHORT).show();
//////                notificationHelper.sendHighPriorityNotification("Student Inside SafeCheck", "", MapsActivity.class);
////                break;
////            case Geofence.GEOFENCE_TRANSITION_EXIT:
////                Toast.makeText(context, "Exiting SafeCheck...", Toast.LENGTH_SHORT).show();
//////                notificationHelper.sendHighPriorityNotification("Student Exiting SafeCheck", "", MapsActivity.class);
////                break;
////        }
//
//        switch (transitionType) {
//            case Geofence.GEOFENCE_TRANSITION_ENTER:
//                Toast.makeText(context, "Entering SafeCheck...", Toast.LENGTH_SHORT).show();
//                notificationHelper.sendHighPriorityNotification("Student Entering SafeCheck", "You've entered the area.", MapsActivity.class);
//                break;
//            case Geofence.GEOFENCE_TRANSITION_DWELL:
//                Toast.makeText(context, "Inside SafeCheck...", Toast.LENGTH_SHORT).show();
//                notificationHelper.sendHighPriorityNotification("Student Inside SafeCheck", "You're still inside.", MapsActivity.class);
//                break;
//            case Geofence.GEOFENCE_TRANSITION_EXIT:
//                Toast.makeText(context, "Exiting SafeCheck...", Toast.LENGTH_SHORT).show();
//                notificationHelper.sendHighPriorityNotification("Student Exiting SafeCheck", "You've left the area.", MapsActivity.class);
//                break;
//            default:
//                Log.d(TAG, "Unknown transition type: " + transitionType);
//        }
//    }
//}

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

public class GeofenceBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "GeofenceBroadcastReceiver";
    private static final String PREF_NAME = "GeofencePrefs";
    private static final String KEY_NOTIFICATION_SENT = "notificationSent";

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper = new NotificationHelper(context);

        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);

        if (geofencingEvent.hasError()) {
            Log.d(TAG, "onReceive: Error receiving geofence event...");
            return;
        }

        int transitionType = geofencingEvent.getGeofenceTransition();

        // Get SharedPreferences to track notifications
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        boolean notificationSent = preferences.getBoolean(KEY_NOTIFICATION_SENT, false);

        if (notificationSent) {
            // If the notification has already been sent, skip sending it again
            return;
        }

        switch (transitionType) {
            case Geofence.GEOFENCE_TRANSITION_ENTER:
                Toast.makeText(context, "Entering SafeCheck...", Toast.LENGTH_SHORT).show();
                notificationHelper.sendHighPriorityNotification("Student Entering SafeCheck", "You've entered the area.", MapsActivity.class);
                break;
            case Geofence.GEOFENCE_TRANSITION_DWELL:
                Toast.makeText(context, "Inside SafeCheck...", Toast.LENGTH_SHORT).show();
                notificationHelper.sendHighPriorityNotification("Student Inside SafeCheck", "You're still inside.", MapsActivity.class);
                break;
            case Geofence.GEOFENCE_TRANSITION_EXIT:
                Toast.makeText(context, "Exiting SafeCheck...", Toast.LENGTH_SHORT).show();
                notificationHelper.sendHighPriorityNotification("Student Exiting SafeCheck", "You've left the area.", MapsActivity.class);
                break;
            default:
                Log.d(TAG, "Unknown transition type: " + transitionType);
        }

        // Mark that the notification has been sent
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(KEY_NOTIFICATION_SENT, true);
        editor.apply();

        // Reset the notification flag after a certain period (e.g., 1 minute)
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            SharedPreferences.Editor editorReset = preferences.edit();
            editorReset.putBoolean(KEY_NOTIFICATION_SENT, false);
            editorReset.apply();
        }, 10000); // 60 seconds
    }
}
