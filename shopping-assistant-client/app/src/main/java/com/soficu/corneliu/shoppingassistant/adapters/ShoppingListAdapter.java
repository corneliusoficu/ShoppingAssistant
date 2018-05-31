package com.soficu.corneliu.shoppingassistant.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soficu.corneliu.shoppingassistant.R;
import com.soficu.corneliu.shoppingassistant.entities.ShoppingList;

import java.util.List;

/**
 * Created by corne on 30-May-18.
 */

public class ShoppingListAdapter extends ObjectsAdapter<ShoppingList> {
    public ShoppingListAdapter(Activity mContext, List<ShoppingList> items) {
        super(mContext, items);
    }

    @Override
    public long getItemId(int i) {
        return ((ShoppingList)getItem(i)).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = mContext.getLayoutInflater();

        if(view == null){
            view = inflater.inflate(R.layout.shopping_list_listview_item, viewGroup, false);
            TextView shoppingListName = view.findViewById(R.id.shopping_list_name_textview_id);

            ShoppingList currentShoppingList = ((ShoppingList)getItem(i));

            shoppingListName.setText(currentShoppingList.getName());

        }

        return view;
    }
}
