package com.soficu.corneliu.shoppingassistant.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by corne on 31-May-18.
 */

public class Store implements Parcelable {

    @SerializedName("store_id")
    long id;

    @SerializedName("store_name")
    String storeName;

    @SerializedName("lat")
    double storeLatitude;

    @SerializedName("long")
    double storeLongitude;

    @SerializedName("distance")
    double distanceToStore;

    public Store(long id, String storeName, double storeLatitude, double storeLongitude) {
        this.id = id;
        this.storeName = storeName;
        this.storeLatitude = storeLatitude;
        this.storeLongitude = storeLongitude;
    }

    protected Store(Parcel in) {
        id = in.readLong();
        storeName = in.readString();
        storeLatitude = in.readDouble();
        storeLongitude = in.readDouble();
        distanceToStore = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(storeName);
        dest.writeDouble(storeLatitude);
        dest.writeDouble(storeLongitude);
        dest.writeDouble(distanceToStore);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Store> CREATOR = new Creator<Store>() {
        @Override
        public Store createFromParcel(Parcel in) {
            return new Store(in);
        }

        @Override
        public Store[] newArray(int size) {
            return new Store[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public double getStoreLatitude() {
        return storeLatitude;
    }

    public void setStoreLatitude(double storeLatitude) {
        this.storeLatitude = storeLatitude;
    }

    public double getStoreLongitude() {
        return storeLongitude;
    }

    public void setStoreLongitude(double storeLongitude) {
        this.storeLongitude = storeLongitude;
    }

    public double getDistanceToStore() {
        return distanceToStore;
    }

    public void setDistanceToStore(double distanceToStore) {
        this.distanceToStore = distanceToStore;
    }
}
