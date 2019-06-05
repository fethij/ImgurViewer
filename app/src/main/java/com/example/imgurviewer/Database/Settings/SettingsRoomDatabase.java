package com.example.imgurviewer.Database.Settings;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.imgurviewer.Models.Database.Settings;

@Database(entities = {Settings.class}, version = 1, exportSchema = false)
public abstract class SettingsRoomDatabase extends RoomDatabase {

    private final static String SETTINGS_DATABASE = "settings_database";

    public abstract SettingsDao settingsDao();

    private static volatile SettingsRoomDatabase INSTANCE;

    public static SettingsRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (SettingsRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SettingsRoomDatabase.class, SETTINGS_DATABASE)
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}
