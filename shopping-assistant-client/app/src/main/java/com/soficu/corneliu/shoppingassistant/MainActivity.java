package com.soficu.corneliu.shoppingassistant;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.soficu.corneliu.shoppingassistant.services.IShoppingAssistantService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseFragmentActivity {

    private DrawerLayout mDrawerLayout;
    private IShoppingAssistantService mShoppingAssistantService;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setupDrawer();
        this.setupToolbar();
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
                                showFragment("new_shopping_list");
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

        showFragment("shopping_lists");

    }

    private void initializeBackendConnection() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mShoppingAssistantService = retrofit.create(IShoppingAssistantService.class);
    }
}
