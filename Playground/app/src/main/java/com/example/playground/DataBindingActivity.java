package com.example.playground;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.example.playground.data.User;
import com.example.playground.databinding.BindingMainActivityBinding;

public class DataBindingActivity extends Activity {

    private User mUser = new User("Johns");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BindingMainActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.binding_main_activity);
        binding.setUser(mUser);
        binding.setHandlers(this);
    }

    public void onCustomClickButton(View view) {
        String newName = ((EditText) findViewById(R.id.editText)).getText().toString();
        mUser.setName(newName);
    }
}
