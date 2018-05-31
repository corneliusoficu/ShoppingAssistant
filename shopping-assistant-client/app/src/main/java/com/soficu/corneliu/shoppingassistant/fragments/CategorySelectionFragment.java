package com.soficu.corneliu.shoppingassistant.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.soficu.corneliu.shoppingassistant.R;
import com.soficu.corneliu.shoppingassistant.adapters.CategoriesAdapter;
import com.soficu.corneliu.shoppingassistant.adapters.ObjectsAdapter;
import com.soficu.corneliu.shoppingassistant.connections.Backend;
import com.soficu.corneliu.shoppingassistant.entities.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by corne on 17-May-18.
 */

public class CategorySelectionFragment extends BaseFragment {

    private ListView mCategoriesListView;
    private ObjectsAdapter<Pair<Category, Category>> mCategoriesAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.category_selection_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        load();
    }

    private void load() {
        ActionBar actionBar =  this.activity.getSupportActionBar();

        if(actionBar != null) {
           actionBar.setTitle(R.string.toolbar_choose_category);
        }

        mCategoriesListView = activity.findViewById(R.id.categories_list);
        retrieveCategories();
    }

    private void retrieveCategories() {
        Call<List<Category>> call = Backend.getInstance().listCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(response.isSuccessful()) {
                    enableListOfCategories(response.body());
                } else {

                    Log.e("BackendService", "Error when accessing resource!");
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e("BackendService", t.getMessage());
            }
        });
    }

    private void enableListOfCategories(List<Category> categories) {
        mCategoriesAdapter = new CategoriesAdapter(activity, categories);
        mCategoriesListView.setAdapter(mCategoriesAdapter);
    }
}
