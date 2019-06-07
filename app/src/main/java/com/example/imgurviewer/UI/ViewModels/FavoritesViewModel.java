package com.example.imgurviewer.UI.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.imgurviewer.API.ImgurRepository;
import com.example.imgurviewer.Database.Account.AccountRepository;
import com.example.imgurviewer.Models.Api.FavImage;
import com.example.imgurviewer.Models.Api.FavImages;
import com.example.imgurviewer.Models.Api.Image;
import com.example.imgurviewer.Models.Api.Images;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritesViewModel extends AndroidViewModel {

    private ImgurRepository imgurRepository = new ImgurRepository();

    private MutableLiveData<String> error = new MutableLiveData<>();
    private MutableLiveData<List<FavImage>> images = new MutableLiveData<>();

    public FavoritesViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> getError() {
        return error;
    }

    public MutableLiveData<List<FavImage>> getImages() {
        return images;
    }

    public void getFavorites(String accessToken, String username, String fav_sort){
        imgurRepository.getFavoriteImages(accessToken, username, fav_sort).enqueue(new Callback<FavImages>() {
            @Override
            public void onResponse(Call<FavImages> call, Response<FavImages> response) {
                if (response.isSuccessful() && response.body() != null){
                    images.setValue(response.body().getData());
                }
                else{
                    error.setValue("Api Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<FavImages> call, Throwable t) {
                error.setValue("Api Error: " + t.getMessage());
            }
        });
    }
}
