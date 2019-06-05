package com.example.imgurviewer.Database.Account;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.example.imgurviewer.Models.Database.Account;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AccountRepository {

    private AccountRoomDatabase accountRoomDatabase;
    private AccountDao accountDao;
    private LiveData<Account> currentAccount;

    private Executor executor = Executors.newSingleThreadExecutor();

    public AccountRepository (Context context){
        accountRoomDatabase = AccountRoomDatabase.getDatabase(context);
        accountDao = accountRoomDatabase.accountDao();
        currentAccount = accountDao.getCurrentAccount();
    }

    public LiveData<Account> getCurrentAccount(){
        return currentAccount;
    }

    public void insert (final Account account){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                accountDao.insert(account);
            }
        });
    }

    public void delete (final Account account){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                accountDao.delete(account);
            }
        });
    }

    public void update (final Account account){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                accountDao.update(account);
            }
        });
    }

}
