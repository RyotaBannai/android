package com.example.playground.fragment;

//import android.app.Activity; // can't use getSupportFragmentManager();

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.playground.R;
import com.google.common.collect.Iterables;

import java.util.Arrays;

public class FragmentActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private Class container;

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

        /*
         * Button for fragment3
         * */
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                renderFragment3();
            }
        });

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String fragmentClassName = Iterables.getLast(Arrays.asList(container.getName().split("\\.", 0)));
                 /*
                 * Want to check if fragment allows back operation.
                 * */
//                 switch(fragmentClassName){
//                     case("Fragment1"):
//                     case("Fragment2"):
//                     case("Fragment3"):
//                 }
            }
        });
    }

    protected void renderFragment1() {
        container = Fragment1.class;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction(); // have to be final to access from inner class
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.container, Fragment1.createInstance("This is Fragment1", Color.LTGRAY), "FRAGMENT1");
        fragmentTransaction.commit();
    }

    protected void renderFragment2() {
        container = Fragment2.class;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.container, new Fragment2(), "FRAGMENT2");
        fragmentTransaction.commit();
    }

    protected void renderFragment3() {
        container = Fragment3.class;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.container, new Fragment3(), "FRAGMENT3");
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
//        final Myfragment fragment = (Myfragment) getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT);

//        if (fragment.allowBackPressed()) { // and then you define a method allowBackPressed with the logic to allow back pressed or not
        super.onBackPressed();
//        }
    }
}
