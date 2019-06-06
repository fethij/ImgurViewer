package com.example.imgurviewer.UI.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.imgurviewer.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.getMenu().getItem(2).setChecked(true);

        TextView titleView = findViewById(R.id.settingstTitle);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_favorites:
                    Intent favIntent = new Intent(SettingsActivity.this, FavoritesActivity.class);
                    startActivity(favIntent);
                    break;
                case R.id.navigation_search:
                    Intent searchIntent = new Intent(SettingsActivity.this, MainActivity.class);
                    startActivity(searchIntent);
                    break;
                case R.id.navigation_settings:

                    break;
            }

            return false;
        }
    };
}
