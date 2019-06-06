package com.example.imgurviewer.AppFile;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {

    public static final String DOWNLOAD_NOTIFICATIONS = "my_downloads";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel myChannel = new NotificationChannel(
                    DOWNLOAD_NOTIFICATIONS,
                    "Download notification",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            myChannel.setDescription("This is a notification to show that images are saved");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(myChannel);
        }

    }
}
