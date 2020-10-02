package com.example.playground.fragment;

//import android.app.Activity; // can't use getSupportFragmentManager();

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.playground.R;

public class FragmentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main_activity);

        final FragmentManager fragmentManager = getSupportFragmentManager(); // only supported by AppCompactActivity
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction(); // have to be final to access from inner class

        /*
         * Button for fragment1
         * */
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction.replace(R.id.container, new Fragment1());
                fragmentTransaction.commit();
            }
        });

        /*
         * Button for fragment2
         * */
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction.replace(R.id.container, new Fragment2());
                fragmentTransaction.commit();
            }
        });
    }
}
