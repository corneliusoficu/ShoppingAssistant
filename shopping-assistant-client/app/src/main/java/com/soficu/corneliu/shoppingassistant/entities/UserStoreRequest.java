package com.soficu.corneliu.shoppingassistant.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by corne on 30-May-18.
 */

public class UserStoreRequest {

    @SerializedName("latitude")
    double userLatitude;

    @SerializedName("longitude")
    double userLongitude;

    @SerializedName("items_ids")
    List<Long> itemIds;

    public UserStoreRequest(double userLatitude, double userLongitude, List<Long> itemIds) {
        this.userLatitude = userLatitude;
        this.userLongitude = userLongitude;
        this.itemIds = itemIds;
    }

    public double getUserLatitude() {
        return userLatitude;
    }

    public void setUserLatitude(double userLatitude) {
        this.userLatitude = userLatitude;
    }

    public double getUserLongitude() {
        return userLongitude;
    }

    public void setUserLongitude(double userLongitude) {
        this.userLongitude = userLongitude;
    }

    public List<Long> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<Long> itemIds) {
        this.itemIds = itemIds;
    }
}
