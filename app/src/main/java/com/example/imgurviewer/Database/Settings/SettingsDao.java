package com.example.imgurviewer.Database.Settings;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.imgurviewer.Models.Database.Settings;

@Dao
public interface SettingsDao {

    @Insert
    void insert (Settings setting);

    @Update
    void update (Settings setting);

    @Delete
    void delete (Settings setting);

    @Query("SELECT * FROM settings LIMIT 1")
    public LiveData<Settings> getSettings();
}
