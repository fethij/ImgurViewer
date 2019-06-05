package com.example.imgurviewer.Database.Account;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.imgurviewer.Models.Database.Account;

@Database(entities = {Account.class}, version = 1, exportSchema = false)
public abstract class AccountRoomDatabase extends RoomDatabase {

    private final static String ACCOUNT_DATABASE = "account_database";

    public abstract AccountDao accountDao();

    private static volatile AccountRoomDatabase INSTANCE;

    public static AccountRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (AccountRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AccountRoomDatabase.class, ACCOUNT_DATABASE)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
