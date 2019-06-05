package com.example.imgurviewer.UI;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imgurviewer.BuildConfig;
import com.example.imgurviewer.Models.Database.Account;
import com.example.imgurviewer.R;

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
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loginButton = findViewById(R.id.button);
        logoutButton = findViewById(R.id.button2);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentAccount = loginViewModel.getAccount().getValue();
                if (currentAccount != null)
                    loginViewModel.Delete(loginViewModel.getAccount().getValue());
                else
                    Toast.makeText(MainActivity.this, "It was null...", Toast.LENGTH_LONG)
                            .show();
            }
        });
    }

}
