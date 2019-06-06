package com.example.imgurviewer.UI.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.imgurviewer.API.ImgurRepository;
import com.example.imgurviewer.Database.Settings.SettingsRepository;
import com.example.imgurviewer.Models.Api.Image;
import com.example.imgurviewer.Models.Api.Images;
import com.example.imgurviewer.Models.Database.Settings;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchViewModel extends AndroidViewModel {

    private ImgurRepository imgurRepository = new ImgurRepository();

    private SettingsRepository settingsRepository;
    private LiveData<Settings> currentSettings;

    private MutableLiveData<List<Image>> images = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();

    public SearchViewModel(@NonNull Application application) {
        super(application);
        settingsRepository = new SettingsRepository(application.getApplicationContext());
        currentSettings = settingsRepository.getSettings();
    }

    public MutableLiveData<String> getError() {
        return error;
    }

    public MutableLiveData<List<Image>> getImages() {
        return images;
    }

    public LiveData<Settings> getCurrentSettings () {
        return currentSettings;
    }

    public void searchImages(String keyword, Settings settings){

        imgurRepository.searchImages(keyword, settings)
                .enqueue(new Callback<Images>() {
                    @Override
                    public void onResponse(Call<Images> call, Response<Images> response) {
                        if (response.isSuccessful() && response.body() != null){
                            images.setValue(response.body().getData());
                        }
                        else{
                            error.setValue("Api Error: " + response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<Images> call, Throwable t) {
                        error.setValue("Api Error: " + t.getMessage());
                    }
        });
    }

    public void Insert (Settings settings){
        settingsRepository.insert(settings);
    }

}
