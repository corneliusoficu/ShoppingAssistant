package com.soficu.corneliu.shoppingassistant;

import android.app.SearchManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.soficu.corneliu.shoppingassistant.services.IShoppingAssistantService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseFragmentActivity {

    private DrawerLayout mDrawerLayout;
    private IShoppingAssistantService mShoppingAssistantService;
    private MaterialSearchView searchView;
    private MenuItem searchMenuItem;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_bar, menu);

        searchMenuItem = menu.findItem(R.id.action_search);
        searchMenuItem.setVisible(false);

        searchView = findViewById(R.id.search_view);
        searchView.setMenuItem(searchMenuItem);

        super.onCreateOptionsMenu(menu);
        showFragment("shopping_lists");
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.setupDrawer();
        this.setupToolbar();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initializeBackendConnection();
    }

    @Override
    protected int getFragmentContainer() {
        return R.id.fragments_container;
    }

    public IShoppingAssistantService getBackend() {
        return this.mShoppingAssistantService;
    }

    private void setupToolbar() {
        Toolbar mToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
    }

    private void setupDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();

                        switch (item.getItemId()){
                            case R.id.nav_my_lists:
                                showFragment("shopping_lists");
                                break;
                            case R.id.nav_add_new_list:
                                showFragment("category_selection");
                                break;
                            case R.id.nav_nearby_stores:
                                showFragment("nearby_stores");
                                break;
                            default:
                                break;
                        }

                        return true;
                    }
                }
        );
    }

    public MenuItem getSearchMenuItem() {
        return searchMenuItem;
    }

    private void initializeBackendConnection() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mShoppingAssistantService = retrofit.create(IShoppingAssistantService.class);
    }
}
