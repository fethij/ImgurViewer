package com.example.imgurviewer.UI.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.imgurviewer.Database.Account.AccountRepository;
import com.example.imgurviewer.Database.Settings.SettingsRepository;
import com.example.imgurviewer.Models.Database.Account;
import com.example.imgurviewer.Models.Database.Settings;

public class SettingsViewModel extends AndroidViewModel {

    private SettingsRepository settingsRepository;
    private LiveData<Settings> currentSettings;

    private AccountRepository accountRepository;
    private LiveData<Account> currentUser;

    public SettingsViewModel(@NonNull Application application) {
        super(application);
        settingsRepository = new SettingsRepository(application.getApplicationContext());
        currentSettings = settingsRepository.getSettings();

        accountRepository = new AccountRepository(application.getApplicationContext());
        currentUser = accountRepository.getCurrentAccount();
    }

    public LiveData<Settings> getCurrentSettings() {
        return currentSettings;
    }

    public LiveData<Account> getCurrentUser() {
        return currentUser;
    }

    public void InsertSettings (Settings settings){
        settingsRepository.insert(settings);
    }

    public void UpdateSettings (Settings settings) {
        settingsRepository.update(settings);
    }

    public void DeleteSettings (Settings settings) {
        settingsRepository.delete(settings);
    }

    public void DeleteAccount (Account account) {
        accountRepository.delete(account);
    }


}
