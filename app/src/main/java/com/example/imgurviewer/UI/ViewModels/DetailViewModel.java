package com.example.imgurviewer.UI.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.imgurviewer.API.ImgurRepository;
import com.example.imgurviewer.Models.Api.Favorite;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailViewModel extends AndroidViewModel {

    private ImgurRepository imgurRepository = new ImgurRepository();

    private MutableLiveData<String> error = new MutableLiveData<>();
    private MutableLiveData<String> favoriteStatus = new MutableLiveData<>();

    public DetailViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> getError() {
        return error;
    }
    public MutableLiveData<String> getFavoriteStatus() {
        return favoriteStatus;
    }

    public void favoriteImage(String accessToken, String imageId){
        imgurRepository.favoriteImage(accessToken, imageId).enqueue(new Callback<Favorite>() {
            @Override
            public void onResponse(Call<Favorite> call, Response<Favorite> response) {
                if (response.isSuccessful() && response.body() != null) {
                    favoriteStatus.setValue(response.body().getData());
                } else {
                    error.setValue("Api Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Favorite> call, Throwable t) {
                error.setValue("Api Error: " + t.getMessage());
            }
        });
    }

}
