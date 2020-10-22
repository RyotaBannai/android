package com.example.myproject.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;

import com.example.myproject.common.MyApplication;
import com.example.myproject.repositories.PokemonRepository;
import com.example.myproject.repositories.PokemonRepository.RepositoryCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ref android docs: https://developer.android.com/guide/background/threading?hl=ja
 */

public class ExecutorMainActivity extends ComponentActivity {
    final static String TAG = "ExecutorMainActivity";
    PokemonRepository pokemonRepository;
    ExecutorService executorService;
    private MyApplication myApplication;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        myApplication = (MyApplication) this.getApplicationContext();
//        myApplication.executorService
//        myApplication.mainThreadHandler


        Log.d(TAG, "Activity created.");
        executorService = Executors.newFixedThreadPool(4);
        pokemonRepository = new PokemonRepository(executorService);
        pokemonRepository.makeGetPokemonRequest("pokemon?limit=10", new RepositoryCallback<JSONArray>() {
            @Override
            public void onComplete(JSONArray result) {
                Log.d(TAG, "onCompleted");
                try {
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject object = result.getJSONObject(i);
                        Log.d(TAG, object.toString());
                    }
                } catch (JSONException e) {
                    Log.d(TAG, "Catch exception");
                }
            }
        });
    }
}
