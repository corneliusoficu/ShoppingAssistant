package com.soficu.corneliu.shoppingassistant.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by corne on 31-May-18.
 */

public class ItemResponse implements Parcelable {

    @SerializedName("item_id")
    long itemId;

    @SerializedName("store")
    Store store;

    protected ItemResponse(Parcel in) {
        itemId = in.readLong();
        store = (Store)in.readParcelable(Store.class.getClassLoader());
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(itemId);
        dest.writeParcelable(store, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ItemResponse> CREATOR = new Creator<ItemResponse>() {
        @Override
        public ItemResponse createFromParcel(Parcel in) {
            return new ItemResponse(in);
        }

        @Override
        public ItemResponse[] newArray(int size) {
            return new ItemResponse[size];
        }
    };
}
