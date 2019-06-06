package com.example.imgurviewer.UI.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imgurviewer.Models.Database.Account;
import com.example.imgurviewer.R;
import com.example.imgurviewer.UI.ViewModels.LoginViewModel;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private Button loginButton;
    private Button logoutButton;

    private LoginViewModel loginViewModel;
    private Account currentAccount;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_favorites:
                    Intent favIntent = new Intent(MainActivity.this, FavoritesActivity.class);
                    startActivity(favIntent);
                    break;
                case R.id.navigation_search:
                    //mTextMessage.setText(R.string.title_search);
                    break;
                case R.id.navigation_settings:
                    Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
                    startActivity(settingsIntent);
                    break;
            }

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = findViewById(R.id.message);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.getMenu().getItem(0).setChecked(true);

        loginButton = findViewById(R.id.button);
        logoutButton = findViewById(R.id.button2);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginViewModel.getAccount().observe(this, new Observer<Account>() {
            @Override
            public void onChanged(@Nullable Account account) {
                currentAccount = account;
                if (currentAccount != null)
                    mTextMessage.setText("Logged in as: " + account.getUsername());
                else
                    mTextMessage.setText("Not logged in");
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentAccount != null) {
                    loginViewModel.Delete(loginViewModel.getAccount().getValue());
                    mTextMessage.setText("User was logged out");
                }
                else {
                    Toast.makeText(MainActivity.this, "It was null...", Toast.LENGTH_LONG)
                            .show();
                    mTextMessage.setText("No Logged In User!");
                }
            }
        });
    }

}
