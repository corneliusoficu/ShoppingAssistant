package com.soficu.corneliu.shoppingassistant.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.soficu.corneliu.shoppingassistant.R;
import com.soficu.corneliu.shoppingassistant.adapters.ItemsAdapter;
import com.soficu.corneliu.shoppingassistant.adapters.ObjectsAdapter;
import com.soficu.corneliu.shoppingassistant.entities.Category;
import com.soficu.corneliu.shoppingassistant.entities.Item;
import com.soficu.corneliu.shoppingassistant.features.ISearchProvider;
import com.soficu.corneliu.shoppingassistant.listeners.DebouncedListener;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by corne on 17-May-18.
 */

public class ItemsSelectionFragment extends BaseFragment implements ISearchProvider {

    private Category mCategory;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private Button mContinueButton;
    private EditText mShoppingListNameEditText;

    private ListView mItemsListView;
    private ObjectsAdapter<Item> mItemsAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mCategory = (Category) getArguments().getSerializable("category");
        return inflater.inflate(R.layout.items_selection_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        mToolbar = activity.findViewById(R.id.toolbar);
        mDrawerLayout = activity.findViewById(R.id.drawer_layout);
        mItemsListView = activity.findViewById(R.id.items_list);
        mContinueButton = activity.findViewById(R.id.continue_to_shopping_list_name_button);
        mShoppingListNameEditText = activity.findViewById(R.id.shopping_list_name_edit_text);

        ActionBar actionBar =  this.activity.getSupportActionBar();

        if(actionBar != null) {
            actionBar.setTitle(R.string.toolbar_choose_items);
        }

        this.retrieveItems();

        setContinueButtonListener();


    }

    private void retrieveItems() {
        Call<List<Item>> call = this.activity.getBackend().listItems(mCategory.getId());
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if(response.isSuccessful()) {
                    displayItemsList(response.body());
                } else {
                    Log.e("BackendService", "Error when accessing resource!");
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Log.e("BackendService", t.getMessage());
            }
        });
    }

    private void displayItemsList(List<Item> items) {
        mItemsAdapter = new ItemsAdapter(activity, items);
        mItemsListView.setAdapter(mItemsAdapter);
    }

    private void setContinueButtonListener() {
        mContinueButton.setOnClickListener(new DebouncedListener() {

            @Override
            public void performClick(View view) {
                List<Item> selectedItems = ((ItemsAdapter)mItemsAdapter).getCheckedItems();

                if(selectedItems.size() == 0) {
                    Toast.makeText(activity, "No shopping items selected!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String shoppingListName = mShoppingListNameEditText.getText().toString();

                if(StringUtils.isEmpty(shoppingListName)) {
                    Toast.makeText(activity, "No shopping list name provided!", Toast.LENGTH_SHORT).show();
                    return;
                }

                
            }
        });
    }
}
