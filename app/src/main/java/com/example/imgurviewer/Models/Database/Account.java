package com.example.imgurviewer.Models.Database;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "account")
public class Account {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo (name = "userName")
    private String username;

    @ColumnInfo (name = "accessToken")
    private String accessToken;

    @ColumnInfo (name = "refreshToken")
    private String refreshToken;

    @ColumnInfo (name = "expiresIn")
    private long expiresIn;
}
