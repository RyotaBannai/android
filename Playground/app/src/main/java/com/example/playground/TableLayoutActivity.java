package com.example.playground;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

/*
* Calculator:
* provides 1-9 buttons and is only capable of accumulating number and show the total.
* */

public class TableLayoutActivity extends Activity {
    private int count = 0;
    private ArrayList<View> buttons = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_layout);

        for (int i = 1; i < 10; i++) {
            int id = getResources().getIdentifier("button" + String.valueOf(i), "id", getPackageName());
            final Button button = (Button) findViewById(id);
            buttons.add(button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String buttonName = v.getResources().getResourceEntryName(v.getId());
//                    System.out.println(v.getTag().toString()); // get tag value
                    switch (buttonName) {
                        case "button1":
                            count += 1;
                            break;
                        case "button2":
                            count += 2;
                            break;
                        case "button3":
                            count += 3;
                            break;
                        case "button4":
                            count += 4;
                            break;
                        case "button5":
                            count += 5;
                            break;
                        case "button6":
                            count += 6;
                            break;
                        case "button7":
                            count += 7;
                            break;
                        case "button8":
                            count += 8;
                            break;
                        case "button9":
                            count += 9;
                            break;
                        default:
                            Log.d("TableLayoutActivity", "unexpected button was pressed.");
                            break;
                    }
                    ((TextView) findViewById(R.id.total)).setText(Integer.toString(count));
                }
            });
        }
    }
}
