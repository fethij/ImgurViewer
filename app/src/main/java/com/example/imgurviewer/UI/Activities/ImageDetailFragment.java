package com.example.imgurviewer.UI.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.imgurviewer.Models.Api.FavImage;
import com.example.imgurviewer.Models.Database.Account;
import com.example.imgurviewer.R;
import com.example.imgurviewer.UI.ViewModels.DetailViewModel;
import com.example.imgurviewer.UI.ViewModels.LoginViewModel;

public class ImageDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";
    private FavImage favImage;
    private Account currentAccount;
    private DetailViewModel detailViewModel;
    private LoginViewModel loginViewModel;

    private ImageView imageView;
    private TextView titleText;
    private TextView descriptionText;
    private Button favoriteButton;
    private Button saveButton;

    private Activity mActivity;
    private NotificationManagerCompat notificationManager;


    public ImageDetailFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments().containsKey(ARG_ITEM_ID)){
            favImage = getArguments().getParcelable(ARG_ITEM_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_image_detail, container, false);

        if (favImage != null){

            initLoginViewModel(rootView);
            initDetailViewModel(rootView);

            imageView = rootView.findViewById(R.id.detailImageView);
            Glide.with(rootView).load(favImage.getLink()).into(imageView);

            titleText = rootView.findViewById(R.id.detailTitleValue);
            titleText.setText(favImage.getTitle());

            descriptionText = rootView.findViewById(R.id.detailDescriptionValue);
            descriptionText.setText(favImage.getDescription());

            favoriteButton = rootView.findViewById(R.id.detailFavoriteButton);
            favoriteButton.setText(getString(R.string.btnUnfavoriteText));
            favoriteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //detailViewModel.favoriteImage(currentAccount.getAccessToken(), favImage.getId());
                }
            });

            //initSaveButton(rootView);

        }

        return rootView;
    }



    private void initDetailViewModel(View rootView) {

    }

    private void initLoginViewModel(View rootView) {

    }
}
