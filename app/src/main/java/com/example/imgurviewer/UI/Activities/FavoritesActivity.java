package com.example.imgurviewer.UI.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imgurviewer.Models.Api.FavImage;
import com.example.imgurviewer.Models.Api.Favorite;
import com.example.imgurviewer.Models.Api.Image;
import com.example.imgurviewer.Models.Database.Account;
import com.example.imgurviewer.Models.Database.Settings;
import com.example.imgurviewer.R;
import com.example.imgurviewer.UI.Adapters.FavoritesAdapter;
import com.example.imgurviewer.UI.ViewModels.FavoritesViewModel;
import com.example.imgurviewer.UI.ViewModels.SettingsViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener {

    public static final String FAVORITE_DETAILS = "FavoriteDetails";
    public static final int FAVORITE_CODE = 8412;
    private Boolean didFirstCall = false;

    private List<FavImage> images = new ArrayList<>();
    private FavoritesViewModel favoritesViewModel;
    private SettingsViewModel settingsViewModel;
    private FavoritesAdapter adapter;
    private RecyclerView favRecyclerView;
    private GestureDetector gestureDetector;

    private Account currentAccount;
    private Settings currentSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.getMenu().getItem(1).setChecked(true);

        initSettingsViewModel();
        initFavoritesViewModel();
        initRecyclerView();
    }

    private void initRecyclerView() {
        adapter = new FavoritesAdapter(images);
        favRecyclerView = findViewById(R.id.favoriteRecyclerView);
        favRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        favRecyclerView.setAdapter(adapter);
        favRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));

        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e){
                return true;
            }
        });
        favRecyclerView.addOnItemTouchListener(this);
    }

    private void initFavoritesViewModel() {
        favoritesViewModel = ViewModelProviders.of(this).get(FavoritesViewModel.class);

        favoritesViewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(FavoritesActivity.this, s, Toast.LENGTH_LONG)
                        .show();
                Log.d("ImgurViewer", s);
            }
        });

        favoritesViewModel.getImages().observe(this, new Observer<List<FavImage>>() {
            @Override
            public void onChanged(@Nullable List<FavImage> imageList) {
                images = imageList;
                updateUI();
            }
        });
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
                    performApiCall();
            }
        });

        settingsViewModel.getCurrentUser().observe(this, new Observer<Account>() {
            @Override
            public void onChanged(@Nullable Account account) {
                currentAccount = account;
                if (account == null){
                    Toast.makeText(FavoritesActivity.this, "User is not logged in!", Toast.LENGTH_LONG)
                            .show();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
                else
                    performApiCall();
            }
        });
    }

    public void performApiCall() {
        if (currentSettings != null && currentAccount != null && !didFirstCall){
            didFirstCall = true;
            favoritesViewModel.getFavorites(currentAccount.getAccessToken(), currentAccount.getUsername(), currentSettings.getFav_sort());
        }
    }

    private void updateUI(){
        if (adapter == null){
            adapter = new FavoritesAdapter(images);
            favRecyclerView.setAdapter(adapter);
        }
        else
            adapter.updateImages(images);
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


    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if (child != null){
            int adapterPosition = rv.getChildAdapterPosition(child);
            if (gestureDetector.onTouchEvent(e)){
                Intent intent = new Intent(FavoritesActivity.this, ImageDetailActivity.class);
                intent.putExtra(FAVORITE_DETAILS, images.get(adapterPosition));
                startActivityForResult(intent, FAVORITE_CODE);
            }
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }
}
