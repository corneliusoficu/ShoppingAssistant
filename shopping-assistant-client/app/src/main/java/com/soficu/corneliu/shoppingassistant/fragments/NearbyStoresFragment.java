package com.soficu.corneliu.shoppingassistant.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.soficu.corneliu.shoppingassistant.R;
import com.soficu.corneliu.shoppingassistant.adapters.NearbyStoresAdapter;
import com.soficu.corneliu.shoppingassistant.adapters.ObjectsAdapter;
import com.soficu.corneliu.shoppingassistant.entities.ItemResponse;
import com.soficu.corneliu.shoppingassistant.entities.NearbyStore;
import com.soficu.corneliu.shoppingassistant.entities.Store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by corne on 17-May-18.
 */

public class NearbyStoresFragment extends BaseFragment {

    BroadcastReceiver mBroadCastReceiver = null;

    TextView noStoresFoundTextView = null;
    ListView nearbyStoresListView = null;
    ObjectsAdapter<NearbyStore> mAdapter = null;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.nearby_stores_fragment, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        noStoresFoundTextView = activity.findViewById(R.id.no_stores_found_message);
        nearbyStoresListView = activity.findViewById(R.id.nearby_stores_listview_id);

        intializeBroadCastReceiver();
        LocalBroadcastManager.getInstance(activity).registerReceiver(
                mBroadCastReceiver, new IntentFilter("NearbyStoresUpdates")
        );

    }

    private void intializeBroadCastReceiver() {
        mBroadCastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                List<NearbyStore> nearbyStores = intent.getParcelableArrayListExtra("NearbyStores");
                updateNearbyStoresList(nearbyStores);

            }
        };
    }

    private void setAdapterNearbyStoresAdapterList(List<NearbyStore> nearbyStores){

        if(mAdapter == null){
            mAdapter = new NearbyStoresAdapter(activity, nearbyStores);
            nearbyStoresListView.setAdapter(mAdapter);
        } else {
            mAdapter.clear();
            if(nearbyStores != null){
                for(NearbyStore nearbyStore: nearbyStores){
                    mAdapter.insert(nearbyStore);
                }
            }
        }

        mAdapter.notifyDataSetChanged();





    }

    private void updateNearbyStoresList(List<NearbyStore> nearbyStores){
        if(nearbyStores == null){
            noStoresFoundTextView.setVisibility(View.VISIBLE);
        } else if(nearbyStores.size() == 0) {
            noStoresFoundTextView.setVisibility(View.VISIBLE);
        } else {
            noStoresFoundTextView.setVisibility(View.INVISIBLE);
        }

        nearbyStoresListView.removeAllViewsInLayout();
        setAdapterNearbyStoresAdapterList(nearbyStores);

    }

    private void convertToNearbyStoresFormat(ArrayList<ItemResponse> itemResponseArray){
        HashMap<Store, Integer> nearbyStoresInformation = new HashMap<>();
    }
}
