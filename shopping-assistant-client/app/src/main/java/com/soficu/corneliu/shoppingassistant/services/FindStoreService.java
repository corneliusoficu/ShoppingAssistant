package com.soficu.corneliu.shoppingassistant.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.soficu.corneliu.shoppingassistant.connections.Backend;
import com.soficu.corneliu.shoppingassistant.connections.Database;
import com.soficu.corneliu.shoppingassistant.entities.ItemResponse;
import com.soficu.corneliu.shoppingassistant.entities.NearbyStore;
import com.soficu.corneliu.shoppingassistant.entities.Store;
import com.soficu.corneliu.shoppingassistant.entities.UserStoreRequest;
import com.soficu.corneliu.shoppingassistant.features.INewCoordinates;
import com.soficu.corneliu.shoppingassistant.listeners.UserLocationListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by corne on 30-May-18.
 */

public class FindStoreService extends Service implements INewCoordinates{

    UserLocationListener locationListener;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        Log.i("FindStoreService", "The service has been created!");
        locationListener = new UserLocationListener(this, this);
    }

    @Override
    public void onDestroy() {
        Log.i("FindStoreService", "The service has been destroyed!");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void handleNewCoordinates(double latitude, double longitude) {
        Log.i("FindStoreService", "Received new coordinates: " + latitude + ", " + longitude);
        new SendDataToServerTask(this, latitude, longitude).execute();
    }

    public void convertToNearbyStoresFormat(List<ItemResponse> itemsStores){

        List<NearbyStore> nearbyStores = new ArrayList<>();

        for(ItemResponse itemResponse : itemsStores){
            boolean found = false;
            for(NearbyStore nearbyStore : nearbyStores){
                if(nearbyStore.getStore().getId() == itemResponse.getStore().getId()){
                    nearbyStore.addNewItemId(itemResponse.getItemId());
                    found = true;
                    break;
                }
            }
            if(!found){
                nearbyStores.add(new NearbyStore(itemResponse.getStore()));
            }
        }

        Intent intent = new Intent("NearbyStoresUpdates");
        intent.putParcelableArrayListExtra("NearbyStores", (ArrayList<NearbyStore>) nearbyStores);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }


    private class SendDataToServerTask extends AsyncTask<String, ItemResponse, List<ItemResponse>>{

        double userLatitude, userLongitude;
        Context context;

        public SendDataToServerTask(Context context, double userLatitude, double userLongitude) {
            this.userLatitude = userLatitude;
            this.userLongitude = userLongitude;
            this.context = context;
        }



        @Override
        protected List<ItemResponse> doInBackground(String... strings) {
            List<Long> uniqueItemIds = Database.getInstance(context).itemsDao().getAllUniqueIds();

            UserStoreRequest userStoreRequest = new UserStoreRequest(userLatitude, userLongitude, uniqueItemIds);
            Call <List<ItemResponse>> call = Backend.getInstance().getStoresAround(userStoreRequest);

            call.enqueue(new Callback<List<ItemResponse>>() {
                @Override
                public void onResponse(Call<List<ItemResponse>> call, Response<List<ItemResponse>> response) {
                    if(response.isSuccessful()) {
                        List<ItemResponse> itemsStores = response.body();
                        if (itemsStores != null) {
                            convertToNearbyStoresFormat(itemsStores);
                        }
                    } else {
                        try {
                            String message = response.errorBody().string();
                            Log.e("FindStoreService", message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Log.e("BackendService", "Error when accessing resource!");
                    }
                }

                @Override
                public void onFailure(Call<List<ItemResponse>> call, Throwable t) {
                    Log.e("BackendService", t.getMessage());
                }
            });

            return null;
        }
    }
}
