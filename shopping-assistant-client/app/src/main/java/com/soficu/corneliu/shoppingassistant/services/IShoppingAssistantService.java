package com.soficu.corneliu.shoppingassistant.services;

import com.soficu.corneliu.shoppingassistant.entities.Category;
import com.soficu.corneliu.shoppingassistant.entities.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IShoppingAssistantService {
    @GET("categories")
    Call<List<Category>> listCategories();

    @GET("items/category/{id}")
    Call<List<Item>> listItems(@Path("id") int categoryId);
}
