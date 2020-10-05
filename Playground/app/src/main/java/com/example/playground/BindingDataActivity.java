package com.example.playground;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.example.playground.data.User2;
import com.example.playground.databinding.MainActivity2Binding;

public class BindingDataActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity2Binding binding = DataBindingUtil.setContentView(this, R.layout.main_activity2);
        binding.setUser2(new User2("Jobs", 30));
    }
}
