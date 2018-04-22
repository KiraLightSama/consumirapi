package com.example.kira.consumirapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ItemService {
    @GET("articles")
    Call<List<Item>> getItem();
}
