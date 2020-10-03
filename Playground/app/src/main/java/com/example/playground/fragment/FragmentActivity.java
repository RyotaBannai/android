package com.example.playground.fragment;

//import android.app.Activity; // can't use getSupportFragmentManager();

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.playground.R;

public class FragmentActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main_activity);

        fragmentManager = getSupportFragmentManager(); // only supported by AppCompactActivity

        // Default fragment
        // 画面が「はじめて」作成された時ににだけ Fragment を追加
        if (savedInstanceState == null) {
            renderFragment1();
        }

        /*
         * Button for fragment1
         * */
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                renderFragment1();
            }
        });

        /*
         * Button for fragment2
         * */
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                renderFragment2();
            }
        });
    }

    protected void renderFragment1() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction(); // have to be final to access from inner class
        fragmentTransaction.replace(R.id.container, Fragment1.createInstance("This is Fragment1", Color.LTGRAY));
        fragmentTransaction.commit();
    }

    protected void renderFragment2() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, new Fragment2());
        fragmentTransaction.commit();
    }
}
