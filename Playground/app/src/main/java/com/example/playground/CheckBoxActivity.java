package com.example.playground;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CheckBox;
import android.widget.Toast;
import android.view.Gravity;
import androidx.annotation.Nullable;

import android.widget.LinearLayout;
import android.view.ViewGroup;

public class CheckBoxActivity extends Activity {
    private final int MP = ViewGroup.LayoutParams.MATCH_PARENT;
    private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
    int duration = Toast.LENGTH_SHORT;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(linearLayout);

        CheckBox cb = new CheckBox(this);
        cb.setText("checkbox");
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Is the view now checked?
//            boolean checked = ((CheckBox) buttonView).isChecked();
                if (isChecked) {
                    CharSequence text = "Checked";
                    Toast.makeText(CheckBoxActivity.this, text, duration).show();
//                    Toast toast = new Toast(getApplicationContext());
//                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//                    toast.setText(text);
//                    toast.setDuration(duration);
//                    toast.setView(layout);
//                    toast.show();
                } else {
                    CharSequence text = "Unchecked";
                    Toast.makeText(getApplication(), text, duration).show(); // getApplicationContext() でも ok
                }
            }
        });
        linearLayout.addView(cb, WC, WC);
    }
}