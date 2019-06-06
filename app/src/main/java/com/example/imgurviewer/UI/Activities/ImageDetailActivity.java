package com.example.imgurviewer.UI.Activities;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.imgurviewer.AppFile.App;
import com.example.imgurviewer.Models.Api.Image;
import com.example.imgurviewer.Models.Database.Account;
import com.example.imgurviewer.R;
import com.example.imgurviewer.UI.ViewModels.DetailViewModel;
import com.example.imgurviewer.UI.ViewModels.LoginViewModel;

import java.io.File;
import java.io.FileOutputStream;

public class ImageDetailActivity extends AppCompatActivity {

    private DetailViewModel detailViewModel;
    private LoginViewModel loginViewModel;

    private Image image;
    private Account currentAccount;

    private ImageView imageView;
    private TextView titleText;
    private TextView descriptionText;
    private Button favoriteButton;
    private Button saveButton;

    private Activity mActivity;
    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        notificationManager = NotificationManagerCompat.from(this);
        mActivity = this;

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        image = getIntent().getParcelableExtra(MainActivity.SEARCH_IMAGE_DETAILS);
        if (image != null){
            initImage(getString(R.string.btnFavoriteText));
        } else{
            image = getIntent().getParcelableExtra(FavoritesActivity.FAVORITE_DETAILS);
            initImage(getString(R.string.btnUnfavoriteText));
        }
    }

    private void initImage(String favoriteBtnText){
        imageView = findViewById(R.id.detailImageView);
        titleText = findViewById(R.id.detailTitleValue);
        descriptionText = findViewById(R.id.detailDescriptionValue);
        favoriteButton = findViewById(R.id.detailFavoriteButton);
        initSaveButton();
        initLoginViewModel();
        initDetailViewModel();

        Glide.with(this).load(image.getLink()).into(imageView);

        titleText.setText(image.getTitle());
        descriptionText.setText(image.getDescription());

        favoriteButton.setText(favoriteBtnText);
        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailViewModel.favoriteImage(currentAccount.getAccessToken(), image.getId());
            }
        });
    }

    private void initDetailViewModel(){
        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);

        detailViewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(ImageDetailActivity.this, s, Toast.LENGTH_LONG)
                        .show();
                Log.d("ImgurViewer", s);
            }
        });

        detailViewModel.getFavoriteStatus().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(ImageDetailActivity.this, s, Toast.LENGTH_LONG)
                        .show();
                switch (s){
                    case "favorited":
                        favoriteButton.setText(getString(R.string.btnUnfavoriteText));
                        SendNotification(getString(R.string.favImageNotificationTitle), getString(R.string.notificationAddFavMessage), R.drawable.ic_favorite_black_24dp);
                        break;
                    case "unfavorited":
                        favoriteButton.setText(getString(R.string.btnFavoriteText));
                        SendNotification(getString(R.string.favImageNotificationTitle), getString(R.string.notificatoinRemoveFavMessage), R.drawable.ic_favorite_border_black_24dp);
                        break;
                }
            }
        });
    }

    private void initSaveButton(){
        saveButton = findViewById(R.id.detailSaveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int permissionCheck = ContextCompat.checkSelfPermission(mActivity.getBaseContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);

                if (permissionCheck != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2569);
                }
                else{
                    if (imageView.getDrawable() != null) {
                        saveImage(((BitmapDrawable) imageView.getDrawable()).getBitmap());

                        String notificationTitle = getString(R.string.notificationSaveTitle);
                        String notificationMessage = "File: " + titleText.getText().toString() + " was saved.";
                        SendNotification(notificationTitle, notificationMessage, R.drawable.ic_file_download_black_24dp);
                    }
                    else
                        Toast.makeText(ImageDetailActivity.this, getString(R.string.imageViewNoBitmap), Toast.LENGTH_LONG)
                                .show();
                }
            }
        });
    }

    private void SendNotification(String title, String message, int drawable) {
        Notification notification = new NotificationCompat.Builder(this, App.DOWNLOAD_NOTIFICATIONS)
                .setSmallIcon(drawable)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManager.notify(1, notification);
    }

    private void saveImage(Bitmap drawable) {
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        String fileName = titleText.getText().toString() + ".png";

        File testFile = new File(root, fileName);
        if (testFile.exists())
            testFile.delete();
        try{
            FileOutputStream out = new FileOutputStream(testFile);
            drawable.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initLoginViewModel(){
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        loginViewModel.getAccount().observe(this, new Observer<Account>() {
            @Override
            public void onChanged(@Nullable Account account) {
                currentAccount = account;
                if (currentAccount == null) {
                    Toast.makeText(ImageDetailActivity.this, "User is not logged in!", Toast.LENGTH_LONG)
                            .show();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
            }
        });

        loginViewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(ImageDetailActivity.this, s, Toast.LENGTH_LONG)
                        .show();
                Log.d("ImgurViewer", s);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 5632:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    if (imageView.getDrawable() != null) {
                        Bitmap loadedImageBitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                        MediaStore.Images.Media.insertImage(getContentResolver(), loadedImageBitmap, titleText.getText().toString(), descriptionText.getText().toString());
                    }
                    else
                        Toast.makeText(ImageDetailActivity.this, getString(R.string.imageViewNoBitmap), Toast.LENGTH_LONG)
                                .show();
                }
                break;

            default:
                break;
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_favorites:
                    Intent favIntent = new Intent(ImageDetailActivity.this, FavoritesActivity.class);
                    startActivity(favIntent);
                    break;
                case R.id.navigation_search:
                    Intent searchIntent = new Intent(ImageDetailActivity.this, MainActivity.class);
                    startActivity(searchIntent);
                    break;
                case R.id.navigation_settings:
                    Intent settingsIntent = new Intent(ImageDetailActivity.this, SettingsActivity.class);
                    startActivity(settingsIntent);
                    break;
            }

            return false;
        }
    };
}
