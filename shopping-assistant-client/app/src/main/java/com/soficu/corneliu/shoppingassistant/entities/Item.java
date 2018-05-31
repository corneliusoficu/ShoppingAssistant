package com.soficu.corneliu.shoppingassistant.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by corne on 28-May-18.
 */

@Entity
public class Item implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="item_id")
    private int itemId;

    @SerializedName("id")
    private int mId;

    @SerializedName("item_name")
    @ColumnInfo(name = "item_name")
    private String mItemName;

    @SerializedName("category_id")
    @ColumnInfo(name = "category_id")
    private String mCategoryId;

    @ColumnInfo(name = "shopping_list_id")
    private long shoppingListId;

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

    public String getCategoryId() {
        return this.mCategoryId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public long getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(long shoppingListId) {
        this.shoppingListId = shoppingListId;
    }
}
