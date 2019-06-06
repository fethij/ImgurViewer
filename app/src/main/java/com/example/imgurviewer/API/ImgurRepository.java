package com.example.imgurviewer.API;

import android.widget.Switch;

import com.example.imgurviewer.BuildConfig;
import com.example.imgurviewer.Models.Api.AccountAuth;
import com.example.imgurviewer.Models.Api.AccountAuthBody;
import com.example.imgurviewer.Models.Api.Favorite;
import com.example.imgurviewer.Models.Api.Images;
import com.example.imgurviewer.Models.Database.Settings;
import com.example.imgurviewer.R;

import retrofit2.Call;



public class ImgurRepository {

    private ImgurApiService imgurApiService = ImgurApi.create();

    public Call<Images> searchImages (String keywords, Settings settings){
        switch (settings.getQueryType()){
            case "any":
                return imgurApiService.searchImagesAny("Client-ID " + BuildConfig.ClientId, settings.getSearch_sort(), settings.getWindow(), keywords, "png");
            default:
                return imgurApiService.searchImages("Client-ID " + BuildConfig.ClientId, settings.getSearch_sort(), settings.getWindow(), keywords, "png");
        }
    }

//    public Call<Images> searchImages (String keywords, String sort, String window) {
//        return imgurApiService.searchImages(BuildConfig.ClientId, keywords, "png", "/" + sort, "/" + window);
//    }

//    public Call<Images> searchImagesAny (String keywords, String sort, String window) {
//        return imgurApiService.searchImagesAny(BuildConfig.ClientId, keywords, "png", "/" + sort, "/" + window);
//    }

    public Call<Favorite> favoriteImage (String accessToken, String image_id){
        return imgurApiService.favoriteImage("Bearer " + accessToken, image_id);
    }

    public Call<AccountAuth> authAccount (String refresh_token){
        AccountAuthBody accountAuthBody = new AccountAuthBody(refresh_token, BuildConfig.ClientId, BuildConfig.ClientSecret, "refresh_token");
        return imgurApiService.authAccount(accountAuthBody);
    }

}
