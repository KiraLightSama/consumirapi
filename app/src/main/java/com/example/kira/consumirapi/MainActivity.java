package com.example.kira.consumirapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ITEM";

    private RecyclerView rvListaItems;
    private ItemsAdapter itemsAdapter;

    private Retrofit retrofit;

    private List<Item> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvListaItems = findViewById(R.id.rvListaItems);

        itemsAdapter = new ItemsAdapter(this);
        rvListaItems.setAdapter(itemsAdapter);
        rvListaItems.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvListaItems.setLayoutManager(layoutManager);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://parcialagenda.000webhostapp.com/myapp/public/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ItemService service = retrofit.create(ItemService.class);
        Call<List<Item>> call = service.getItem();

        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if (response.isSuccessful()){
                    itemList = response.body();
                    itemsAdapter.adicionarListaItems(itemList);
                } else {
                    Log.e(TAG, "onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
