package com.example.playground;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.playground.ViewModels.ScoreViewModel;

public class ScoreCourtMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScoreViewModel model = new ViewModelProvider(this).get(ScoreViewModel.class);
//        model.getUsers().observe(this, users -> {
//            // update UI
//        });
    }
}
