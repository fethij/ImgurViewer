package com.example.imgurviewer.Database.Settings;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.imgurviewer.Models.Database.Settings;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SettingsRepository {

    private SettingsRoomDatabase settingsRoomDatabase;
    private SettingsDao settingsDao;
    private LiveData<Settings> settings;

    private Executor executor = Executors.newSingleThreadExecutor();

    public SettingsRepository(Context context) {
        settingsRoomDatabase = SettingsRoomDatabase.getDatabase(context);
        settingsDao = settingsRoomDatabase.settingsDao();
        settings = settingsDao.getSettings();
    }

    public LiveData<Settings> getSettings () {
        return settings;
    }

    public void insert (final Settings setting){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                settingsDao.insert(setting);
            }
        });
    }

    public void update (final Settings setting){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                settingsDao.update(setting);
            }
        });
    }

    public void delete (final Settings setting){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                settingsDao.delete(setting);
            }
        });
    }
}
