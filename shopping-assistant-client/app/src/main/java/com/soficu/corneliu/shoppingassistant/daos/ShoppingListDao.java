package com.soficu.corneliu.shoppingassistant.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.soficu.corneliu.shoppingassistant.entities.ShoppingList;

/**
 * Created by corne on 30-May-18.
 */

@Dao
public interface ShoppingListDao {
    @Query("SELECT count(*) FROM shoppinglist where name = :name")
    public boolean doesShoppingListWithNameExist(String name);

    @Query("SELECT * FROM shoppinglist")
    public ShoppingList[] getAll();

    @Insert
    public long insertOne(ShoppingList shoppingList);

}
