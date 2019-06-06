package com.example.imgurviewer.UI.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imgurviewer.Models.Api.Image;
import com.example.imgurviewer.Models.Api.Images;
import com.example.imgurviewer.Models.Database.Account;
import com.example.imgurviewer.Models.Database.Settings;
import com.example.imgurviewer.R;
import com.example.imgurviewer.UI.Adapters.ImagesAdapter;
import com.example.imgurviewer.UI.ViewModels.LoginViewModel;
import com.example.imgurviewer.UI.ViewModels.SearchViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener{

    private TextView mTextMessage;
    private Button loginButton;
    private Button logoutButton;

    private LoginViewModel loginViewModel;
    private SearchViewModel searchViewModel;
    private Account currentAccount;

    private EditText searchText;
    private Button searchButton;
    private ImagesAdapter adapter;
    private RecyclerView imagesRecyclerView;
    private GestureDetector gestureDetector;
    private Settings currentSettings;

    public static final String SEARCH_IMAGE_DETAILS = "SearchDetails";
    public static final int SEARCH_IMAGE_CODE = 7852;

    private List<Image> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = findViewById(R.id.message);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.getMenu().getItem(0).setChecked(true);

        initLoginViewModel();
        initSearchViewModel();
        initRecyclerView();

        searchText = findViewById(R.id.searchEditText);

        searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(searchText.getText().toString())) {
                    searchViewModel.searchImages(searchText.getText().toString(), currentSettings);
                }
                else {
                    Toast.makeText(MainActivity.this, getString(R.string.emptySearchMessage), Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

    }

    private void initRecyclerView() {
        adapter = new ImagesAdapter(images);
        imagesRecyclerView = findViewById(R.id.ImagesRecyclerView);
        imagesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL));
        imagesRecyclerView.setAdapter(adapter);

        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e){
                return true;
            }
        });
        imagesRecyclerView.addOnItemTouchListener(this);
    }

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

    private void initSearchViewModel(){
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);

        searchViewModel.getImages().observe(this, new Observer<List<Image>>() {
            @Override
            public void onChanged(@Nullable List<Image> imageList) {
                images = imageList;
                updateUI();
            }
        });

        searchViewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG)
                        .show();
                Log.d("ImgurViewer", s);
            }
        });

        searchViewModel.getCurrentSettings().observe(this, new Observer<Settings>() {
            @Override
            public void onChanged(@Nullable Settings settings) {
                currentSettings = settings;
                if (currentSettings == null)
                    searchViewModel.Insert(new Settings("time", "all", "any", "newest"));
            }
        });

    }

    private void initLoginViewModel(){
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
        loginViewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG)
                        .show();
                Log.d("ImgurViewer", s);
            }
        });

    }

    private void updateUI(){
        if (adapter == null){
            adapter = new ImagesAdapter(images);
            imagesRecyclerView.setAdapter(adapter);
        }
        else
            adapter.updateImages(images);
    }

    private void initButtons(){
        loginButton = findViewById(R.id.button);
        logoutButton = findViewById(R.id.button2);

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

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if (child != null){
            int adapterPosition = rv.getChildAdapterPosition(child);
            if (gestureDetector.onTouchEvent(e)){
                Intent intent = new Intent(MainActivity.this, ImageDetailActivity.class);
                intent.putExtra(SEARCH_IMAGE_DETAILS, images.get(adapterPosition));
                startActivityForResult(intent, SEARCH_IMAGE_CODE);
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
