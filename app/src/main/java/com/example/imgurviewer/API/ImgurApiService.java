package com.example.imgurviewer.API;

import com.example.imgurviewer.Models.Api.AccountAuth;
import com.example.imgurviewer.Models.Api.AccountAuthBody;
import com.example.imgurviewer.Models.Api.Favorite;
import com.example.imgurviewer.Models.Api.Images;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ImgurApiService {

    @GET("/3/gallery/search{sort}{window}")
    Call<Images> searchImages (
            @Header("Authorization") String auth,
            @Query("q") String keywords,
            @Query("q_type") String type,
            @Path(value = "sort") String sort,
            @Path(value = "window") String window
    );

    @GET("/3/gallery/search{sort}{window}")
    Call<Images> searchImagesAny(
            @Header("Authorization") String auth,
            @Query("q_any") String keywords,
            @Query("q_type") String type,
            @Path(value = "sort") String sort,
            @Path(value = "window") String window
    );

    @POST("/3/image/{image_id}/favorite")
    Call<Favorite> favoriteImage(
            @Header("Authorization") String auth,
            @Part(value = "image_id") String image_id
    );

    @POST("/oauth2/token")
    Call<AccountAuth> authAccount(
            @Body AccountAuthBody body
    );
}
