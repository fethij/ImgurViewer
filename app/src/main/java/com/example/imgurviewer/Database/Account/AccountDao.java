package com.example.imgurviewer.Database.Account;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.imgurviewer.Models.Database.Account;

import java.util.List;

@Dao
public interface AccountDao {

    @Insert
    void insert (Account account);

    @Update
    void update (Account account);

    @Delete
    void delete (Account account);

    @Query("SELECT * FROM account LIMIT 1")
    public LiveData<Account> getCurrentAccount();
}
