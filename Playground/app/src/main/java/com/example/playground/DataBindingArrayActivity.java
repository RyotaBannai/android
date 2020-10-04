package com.example.playground;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.example.playground.data.User;
import com.example.playground.databinding.MainActivityBinding;

import java.util.ArrayList;
import java.util.List;

public class DataBindingArrayActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Careful: use Main activity's binding!!
        MainActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.main_activity);

        List<User> users = new ArrayList<>();
        users.add(new User("Johns"));
        users.add(new User("Daredevil"));
        users.add(new User("Flash"));

        binding.setUsers(users);
    }
}
