package ru.navodnikov.denis.locationtracker.app.bg;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.DaggerService;
import ru.navodnikov.denis.locationtracker.R;
import ru.navodnikov.denis.locationtracker.app.ui.MainActivity;

public class ForegroundService extends DaggerService {
    
    @Inject
    SendTrackerContract.LocationSender model;

    public static final String CHANNEL_ID = "ForegroundServiceChannel";
    public static final String TITLE = "Location Tracker";
    public static final String MESSAGE = "Отправка местоположения";


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createNotificationChannel();
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(TITLE)
                .setContentText(MESSAGE)
                .setSmallIcon(R.drawable.ic_icon_app)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1, notification);
        model.sendLocationStart();
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

}
