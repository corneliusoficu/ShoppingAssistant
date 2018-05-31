package com.soficu.corneliu.shoppingassistant.connections;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.soficu.corneliu.shoppingassistant.BuildConfig;
import com.soficu.corneliu.shoppingassistant.services.IShoppingAssistantService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by corne on 30-May-18.
 */

public class Backend {

    private static IShoppingAssistantService service = null;

    private Backend() {}

    public static IShoppingAssistantService getInstance() {
        if(service == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            service = retrofit.create(IShoppingAssistantService.class);
        }

        return service;
    }


}
