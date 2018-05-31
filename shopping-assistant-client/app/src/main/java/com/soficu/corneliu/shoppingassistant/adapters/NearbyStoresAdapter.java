package com.soficu.corneliu.shoppingassistant.adapters;

import android.app.Activity;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soficu.corneliu.shoppingassistant.R;
import com.soficu.corneliu.shoppingassistant.entities.NearbyStore;
import com.soficu.corneliu.shoppingassistant.entities.Store;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by corne on 31-May-18.
 */

public class NearbyStoresAdapter extends ObjectsAdapter<NearbyStore> {
    public NearbyStoresAdapter(Activity mContext, List<NearbyStore> items) {
        super(mContext, items);
    }

    @Override
    public long getItemId(int i) {
        return ((NearbyStore)getItem(i)).getStore().getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = mContext.getLayoutInflater();

        final NearbyStore currentNearbyStore = (NearbyStore)getItem(i);


        if(view == null) {
            view = inflater.inflate(R.layout.nearby_store_list_item, viewGroup, false);

            TextView storeNameTextView = view.findViewById(R.id.store_name_textview);
            TextView numberOfItemsTextView = view.findViewById(R.id.store_items_found_textview);
            TextView distanceToStoreTextView = view.findViewById(R.id.distance_to_store_textview);

            String itemsFoundString = currentNearbyStore.getNumberOfItems() + " items in your shopping list";

            storeNameTextView.setText(currentNearbyStore.getStore().getStoreName());
            numberOfItemsTextView.setText(itemsFoundString);

            String distanceToStoreString = String.valueOf(currentNearbyStore.getStore().getDistanceToStore()) + " m";
            distanceToStoreTextView.setText(distanceToStoreString);
        }

        return view;

    }
}
