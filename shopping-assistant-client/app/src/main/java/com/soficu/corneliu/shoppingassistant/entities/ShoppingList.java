package com.soficu.corneliu.shoppingassistant.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by corne on 30-May-18.
 */

@Entity(indices = {@Index(value = {"name"}, unique = true)})
public class ShoppingList {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name="name")
    private String name;

    @ColumnInfo(name="category_id")
    private long categoryId;

    public ShoppingList(String name, long categoryId) {
        this.name = name;
        this.categoryId = categoryId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
