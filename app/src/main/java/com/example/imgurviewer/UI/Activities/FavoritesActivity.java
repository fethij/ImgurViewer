package com.example.imgurviewer.UI.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.imgurviewer.Models.Api.Favorite;
import com.example.imgurviewer.R;

public class FavoritesActivity extends AppCompatActivity {

    public static final String FAVORITE_DETAILS = "FavoriteDetails";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.getMenu().getItem(1).setChecked(true);


        TextView titleView = findViewById(R.id.favoritesTitle);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_favorites:

                    break;
                case R.id.navigation_search:
                    Intent searchIntent = new Intent(FavoritesActivity.this, MainActivity.class);
                    startActivity(searchIntent);
                    break;
                case R.id.navigation_settings:
                    Intent settingsIntent = new Intent(FavoritesActivity.this, SettingsActivity.class);
                    startActivity(settingsIntent);
                    break;
            }

            return false;
        }
    };


}
