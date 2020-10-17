package com.example.myproject.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myproject.R;
import com.example.myproject.viewModel.NameViewModel;

public class LivelyMainActivity extends AppCompatActivity implements View.OnClickListener{
    private final String TAG = getClass().getSimpleName();
    private NameViewModel model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lively_main_layout);

        /*
         * アプリ コンポーネントは STARTED の状態になるとすぐに、
         * 監視対象の LiveData オブジェクトから最新の値を受け取る
         */

        /*
        * Define callback for observer
        * */
        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d(TAG, s);
                ((TextView)findViewById(R.id.textView)).setText(s);
            }
        };
        model = new ViewModelProvider(this).get(NameViewModel.class);
        model.getCurrentName().observe(this, nameObserver);

        findViewById(R.id.button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        model.getCurrentName().setValue("aaa");
    }
}
