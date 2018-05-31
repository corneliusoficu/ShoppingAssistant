package com.soficu.corneliu.shoppingassistant.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by corne on 31-May-18.
 */

public class NearbyStore implements Parcelable{
    private Store store;
    private List<Long> soldItemsIds;
    private double distance;

    public NearbyStore(Store store) {
        this.store = store;
        this.soldItemsIds = new ArrayList<>();
    }

    public NearbyStore(Store store, double distance) {
        this.store = store;
        this.distance = distance;
        this.soldItemsIds = new ArrayList<>();
    }

    protected NearbyStore(Parcel in) {
        store = in.readParcelable(Store.class.getClassLoader());
        distance = in.readDouble();
        soldItemsIds = in.readArrayList(null);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(store, flags);
        dest.writeDouble(distance);
        dest.writeList(soldItemsIds);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NearbyStore> CREATOR = new Creator<NearbyStore>() {
        @Override
        public NearbyStore createFromParcel(Parcel in) {
            return new NearbyStore(in);
        }

        @Override
        public NearbyStore[] newArray(int size) {
            return new NearbyStore[size];
        }
    };

    public Store getStore() {
        return store;
    }

    public List<Long> getSoldItemsIds() {
        return soldItemsIds;
    }

    public double getDistance() {
        return distance;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void setSoldItemsIds(List<Long> soldItemsIds) {
        this.soldItemsIds = soldItemsIds;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void addNewItemId(Long id){
        if(soldItemsIds == null){
            soldItemsIds = new ArrayList<>();
        }

        soldItemsIds.add(id);
    }

    public int getNumberOfItems() {
        return this.soldItemsIds.size();
    }
}
