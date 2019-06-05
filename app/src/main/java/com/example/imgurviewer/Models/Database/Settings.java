package com.example.imgurviewer.Models.Database;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.v4.app.NotificationCompat;

@Entity (tableName = "settings")
public class Settings {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "search_sort")
    private String search_sort;

    @ColumnInfo(name = "window")
    private String window;

    @ColumnInfo(name = "queryType")
    private String queryType;

    @ColumnInfo(name = "fav_sort")
    private String fav_sort;

    public Settings(String search_sort, String window, String queryType, String fav_sort) {
        this.search_sort = search_sort;
        this.window = window;
        this.queryType = queryType;
        this.fav_sort = fav_sort;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSearch_sort() {
        return search_sort;
    }

    public void setSearch_sort(String search_sort) {
        this.search_sort = search_sort;
    }

    public String getWindow() {
        return window;
    }

    public void setWindow(String window) {
        this.window = window;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getFav_sort() {
        return fav_sort;
    }

    public void setFav_sort(String fav_sort) {
        this.fav_sort = fav_sort;
    }
}
