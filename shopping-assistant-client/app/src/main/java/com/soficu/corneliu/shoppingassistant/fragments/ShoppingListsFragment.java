package com.soficu.corneliu.shoppingassistant.fragments;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.soficu.corneliu.shoppingassistant.R;
import com.soficu.corneliu.shoppingassistant.adapters.ShoppingListAdapter;
import com.soficu.corneliu.shoppingassistant.connections.Database;
import com.soficu.corneliu.shoppingassistant.entities.Item;
import com.soficu.corneliu.shoppingassistant.entities.ShoppingList;
import com.soficu.corneliu.shoppingassistant.features.IAsyncResponse;
import com.soficu.corneliu.shoppingassistant.services.AppDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShoppingListsFragment extends BaseFragment implements IAsyncResponse<List<ShoppingList>>{

    ListView shoppingListListView = null;
    ShoppingListAdapter shoppingListAdapter = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.shopping_lists_fragment, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        this.shoppingListListView = activity.findViewById(R.id.shopping_lists_list_view);

        new GetShoppingListsTask(this, Database.getInstance(activity)).execute();

    }

    @Override
    public void processFinish(List<ShoppingList> shoppingLists) {
        shoppingListAdapter = new ShoppingListAdapter(activity, shoppingLists);
        shoppingListListView.setAdapter(shoppingListAdapter);
    }

    private static class GetShoppingListsTask extends AsyncTask<String, Integer, List<ShoppingList>> {

        private IAsyncResponse<List<ShoppingList>> delegate = null;
        private AppDatabase database = null;

        GetShoppingListsTask(IAsyncResponse<List<ShoppingList>> delegate, AppDatabase database) {
            this.delegate = delegate;
            this.database = database;
        }

        @Override
        protected List<ShoppingList> doInBackground(String... strings) {
            ShoppingList[] shoppingLists = database.shoppingListDao().getAll();
            List<ShoppingList> shoppingListsArray = new ArrayList<ShoppingList>(Arrays.asList(shoppingLists));
            return shoppingListsArray;
        }

        @Override
        protected void onPostExecute(List<ShoppingList> shoppingLists) {
            delegate.processFinish(shoppingLists);
        }
    }
}
