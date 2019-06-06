package com.example.imgurviewer.UI.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.imgurviewer.API.ImgurRepository;
import com.example.imgurviewer.Database.Account.AccountRepository;
import com.example.imgurviewer.Models.Api.AccountAuth;
import com.example.imgurviewer.Models.Database.Account;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {

    private AccountRepository accountRepository;
    private ImgurRepository imgurRepository = new ImgurRepository();

    private LiveData<Account> authAccount;
    private MutableLiveData<String> error = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
        accountRepository = new AccountRepository(application.getApplicationContext());
        authAccount = accountRepository.getCurrentAccount();
    }

    public MutableLiveData<String> getError() {
        return error;
    }
    public LiveData<Account> getAccount() {
        return authAccount;
    }

    public void authAccount(final String refresh_token){

        imgurRepository.authAccount(refresh_token).enqueue(new Callback<AccountAuth>() {
            @Override
            public void onResponse(Call<AccountAuth> call, Response<AccountAuth> response) {
                if (response.isSuccessful() && response.body() != null){

                    Account authenticatedAccount = new Account(
                            response.body().getAccountUsername(),
                            response.body().getAccessToken(),
                            response.body().getRefreshToken(),
                            response.body().getExpiresIn()
                    );

                    Insert(authenticatedAccount);
                } else {
                    error.setValue("Api Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<AccountAuth> call, Throwable t) {
                error.setValue("Api Error: " + t.getMessage());
            }
        });
    }

    public void Insert(Account account){
        accountRepository.insert(account);
    }

    public void Update(Account account){
        accountRepository.update(account);
    }

    public void Delete(Account account){
        accountRepository.delete(account);
    }

}
