package com.example.imgurviewer.UI.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.imgurviewer.Models.Api.Image;
import com.example.imgurviewer.R;

public class ImageDetailActivity extends AppCompatActivity {

    private Image image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        TextView titleView = findViewById(R.id.detailTitle);
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
