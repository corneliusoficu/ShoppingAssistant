package com.soficu.corneliu.shoppingassistant.services;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.soficu.corneliu.shoppingassistant.daos.ItemsDao;
import com.soficu.corneliu.shoppingassistant.daos.ShoppingListDao;
import com.soficu.corneliu.shoppingassistant.entities.Item;
import com.soficu.corneliu.shoppingassistant.entities.ShoppingList;

/**
 * Created by corne on 30-May-18.
 */

@Database(entities = {ShoppingList.class, Item.class}, version = 5)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ShoppingListDao shoppingListDao();
    public abstract ItemsDao itemsDao();
}
