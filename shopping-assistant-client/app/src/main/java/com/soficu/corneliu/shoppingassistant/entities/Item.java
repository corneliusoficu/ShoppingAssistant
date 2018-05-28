package com.soficu.corneliu.shoppingassistant.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by corne on 28-May-18.
 */

public class Item implements Serializable {

    @SerializedName("id")
    private int mId;

    @SerializedName("item_name")
    private String mItemName;

    @SerializedName("category_id")
    private String mCategoryId;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getItemName() {
        return mItemName;
    }

    public void setItemName(String itemName) {
        this.mItemName = itemName;
    }

    public void setCategoryId(String categoryId) {
        this.mCategoryId = categoryId;
    }

    public String getmCategoryId() {
        return this.mCategoryId;
    }


}
