package com.soficu.corneliu.shoppingassistant.services;

import com.soficu.corneliu.shoppingassistant.entities.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

public interface IShoppingAssistantService {
    @GET("categories")
    Call<List<Category>> listCategories();
}
