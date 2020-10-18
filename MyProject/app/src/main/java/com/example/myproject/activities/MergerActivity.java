package com.example.myproject.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myproject.R;
import com.example.myproject.viewModel.MergerViewModel;
import com.example.myproject.viewModel.NameViewModel;

public class MergerActivity extends AppCompatActivity implements View.OnClickListener{
    private final String TAG = getClass().getSimpleName();
    private MergerViewModel model;
    private Button firstNameButton;
    private Button lastNameButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.merger_main_activity);

        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d(TAG, s);
                ((TextView)findViewById(R.id.fullName)).setText(s);
            }
        };

        model = new ViewModelProvider(this).get(MergerViewModel.class);
        model.getFullName().observe(this, nameObserver);

        firstNameButton = findViewById(R.id.firstNameButton);
        firstNameButton.setOnClickListener(this);

        lastNameButton = findViewById(R.id.lastNameButton);
        lastNameButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v != null && v == firstNameButton){
            EditText tempFirstName = findViewById(R.id.firstName);
            model.getFirstName().setValue(tempFirstName.getText().toString());
            tempFirstName.setText("");
        }
        else {
            EditText tempLastName = findViewById(R.id.lastName);
            model.getLastName().setValue(tempLastName.getText().toString());
            tempLastName.setText("");
        }
    }
}
