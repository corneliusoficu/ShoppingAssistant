package com.soficu.corneliu.shoppingassistant.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.soficu.corneliu.shoppingassistant.entities.Item;
import com.soficu.corneliu.shoppingassistant.entities.ShoppingList;

import java.util.List;

/**
 * Created by corne on 30-May-18.
 */

@Dao
public interface ItemsDao {
    @Insert
    public void insertMultiple(Item[] items);

    @Query("SELECT DISTINCT mId FROM item")
    public List<Long> getAllUniqueIds();
}
