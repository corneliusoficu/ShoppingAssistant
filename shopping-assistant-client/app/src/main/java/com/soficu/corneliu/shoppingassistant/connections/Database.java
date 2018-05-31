package com.soficu.corneliu.shoppingassistant.connections;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.soficu.corneliu.shoppingassistant.services.AppDatabase;

/**
 * Created by corne on 30-May-18.
 */

public class Database {

    private static AppDatabase database = null;

    private Database() {}

    public static AppDatabase getInstance(Context context) {

        if(database == null){
            database = Room.databaseBuilder(context, AppDatabase.class, "shopping-assistant")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return database;
    }
}
