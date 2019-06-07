package com.example.imgurviewer.UI.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imgurviewer.Models.Database.Account;
import com.example.imgurviewer.Models.Database.Settings;
import com.example.imgurviewer.R;
import com.example.imgurviewer.UI.ViewModels.SettingsViewModel;

public class SettingsActivity extends AppCompatActivity {

    private SettingsViewModel settingsViewModel;
    private Settings currentSettings;
    private Account currentAccount;

    private Spinner searchSortSpinner;
    private Spinner searchWindowSpinner;
    private Spinner searchQueryTypeSpinner;
    private Spinner favSortSpinner;

    private Button btnSaveSettings;
    private Button btnSignOut;

    private TextView currentUserLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.getMenu().getItem(2).setChecked(true);

        initSettingsViewModel();
        initCurrentUserLabel();
        //initButtons();
    }

    private void initButtons() {
        btnSaveSettings = findViewById(R.id.settingsSaveButton);
        btnSaveSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentSettings != null) {
                    currentSettings.setSearch_sort(searchSortSpinner.getSelectedItem().toString());
                    currentSettings.setWindow(searchWindowSpinner.getSelectedItem().toString());
                    currentSettings.setQueryType(searchQueryTypeSpinner.getSelectedItem().toString());
                    currentSettings.setFav_sort(favSortSpinner.getSelectedItem().toString());
//                    String searchSort = searchSortSpinner.getSelectedItem().toString();
//                    String searchWindow = searchWindowSpinner.getSelectedItem().toString();
//                    String searchQuery = searchQueryTypeSpinner.getSelectedItem().toString();
//                    String favSort = favSortSpinner.getSelectedItem().toString();
//
//                    Settings updatedSettings = new Settings(searchSort, searchWindow, searchQuery, favSort);

                    settingsViewModel.UpdateSettings(currentSettings);
                    Toast.makeText(SettingsActivity.this, getString(R.string.settingsUpdatesSettingsMessage), Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        btnSignOut = findViewById(R.id.settingsSignInButton);
        if (currentAccount != null) {
            btnSignOut.setText(getString(R.string.settingsBtnSignOut));
            btnSignOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    settingsViewModel.DeleteAccount(currentAccount);
                    Toast.makeText(SettingsActivity.this, getString(R.string.settingsUserSignOut), Toast.LENGTH_LONG)
                            .show();
                }
            });
        }
        else {
            btnSignOut.setText(getString(R.string.settingsBtnSignIn));
            btnSignOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
            });
        }
    }

    private void initCurrentUserLabel() {
        currentUserLabel = findViewById(R.id.settingsUsernameValue);
        if (currentAccount != null){
            currentUserLabel.setText(currentAccount.getUsername());
        }
        else{
            currentUserLabel.setText(getString(R.string.settingsUsernameValueNoUser));
        }
    }

    private void initSpinners() {
        searchSortSpinner = findViewById(R.id.settingsSearchSortSpinner);
        searchWindowSpinner = findViewById(R.id.settingsSearchWindowSpinner);
        searchQueryTypeSpinner = findViewById(R.id.settingsQueryTypeSpinner);
        favSortSpinner = findViewById(R.id.settingsFavSearchSpinner);

        if (currentSettings != null){
            searchSortSpinner.setSelection(((ArrayAdapter)searchSortSpinner.getAdapter()).getPosition(currentSettings.getSearch_sort()));
            searchWindowSpinner.setSelection(((ArrayAdapter)searchWindowSpinner.getAdapter()).getPosition(currentSettings.getWindow()));
            searchQueryTypeSpinner.setSelection(((ArrayAdapter)searchQueryTypeSpinner.getAdapter()).getPosition(currentSettings.getQueryType()));
            favSortSpinner.setSelection(((ArrayAdapter)favSortSpinner.getAdapter()).getPosition(currentSettings.getFav_sort()));
        }
    }

    private void initSettingsViewModel() {
        settingsViewModel = ViewModelProviders.of(this).get(SettingsViewModel.class);

        settingsViewModel.getCurrentSettings().observe(this, new Observer<Settings>() {
            @Override
            public void onChanged(@Nullable Settings settings) {
                currentSettings = settings;
                if (settings == null){
                    currentSettings = new Settings("time", "all", "any", "newest");
                }
                else
                    initSpinners();
            }
        });

        settingsViewModel.getCurrentUser().observe(this, new Observer<Account>() {
            @Override
            public void onChanged(@Nullable Account account) {
                currentAccount = account;
                initButtons();
                initCurrentUserLabel();
            }
        });
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
