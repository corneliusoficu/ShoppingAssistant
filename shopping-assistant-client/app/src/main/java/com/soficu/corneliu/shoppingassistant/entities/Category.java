package com.soficu.corneliu.shoppingassistant.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Category implements Serializable {

    @SerializedName("id")
    private int mId;

    @SerializedName("category_name")
    private String mCategoryName;

    @SerializedName("image_link")
    private String mImageURL;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getCategoryName() {
        return mCategoryName;
    }

    public void setCategoryName(String categoryName) {
        this.mCategoryName = categoryName;
    }

    public void setImageURL(String imageURL) {
        this.mImageURL = imageURL;
    }

    public String getImageURL() {
        return this.mImageURL;
    }


}
