package com.soficu.corneliu.shoppingassistant.adapters;

import android.app.Activity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.soficu.corneliu.shoppingassistant.R;
import com.soficu.corneliu.shoppingassistant.entities.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by corne on 28-May-18.
 */

public class ItemsAdapter extends ObjectsAdapter<Item> {

    private SparseArray<Item> mChecked;

    public ItemsAdapter(Activity mContext, List<Item> items) {
        super(mContext, items);

        mChecked = new SparseArray<>(items.size());
    }

    @Override
    public long getItemId(int i) {
        return ((Item)getItem(i)).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final Item currentItem = (Item)getItem(i);

        LayoutInflater inflater = mContext.getLayoutInflater();

        if(view == null) {
            view = inflater.inflate(R.layout.list_view_item_name, viewGroup, false);

            CheckBox itemNameCheckBox = view.findViewById(R.id.list_item_checkbox_id);
            itemNameCheckBox.setText(currentItem.getItemName());

            itemNameCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setCheckedItem(currentItem);
                }
            });
        }

        return view;
    }

    public void setCheckedItem(Item item) {
        if (mChecked.get(item.getId()) != null) {
            mChecked.remove(item.getId());
        } else {
            mChecked.put(item.getId(), item);
        }
    }

    public ArrayList<Item> getCheckedItems() {
        ArrayList<Item> checkedItems = new ArrayList<>();
        for(int index = 0; index < mChecked.size(); index++) {
            int key = mChecked.keyAt(index);
            checkedItems.add(mChecked.get(key));
        }
        return checkedItems;
    }
}
