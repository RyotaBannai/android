package com.example.playground;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 * Ref: https://www.javatpoint.com/android-spinner-example
 * */

public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

    String[] country = {"India", "USA", "China", "JAPAN"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_spinner);

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        // Creating Adapter is necessary
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, country); // build in layout.
        aa.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(aa);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(SpinnerActivity.this, country[position], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }
}
