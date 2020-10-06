package com.example.playground.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.playground.R;

import javax.annotation.Nullable;

public class CustomLayout extends LinearLayout implements View.OnClickListener {
    private TextView viewText;
    private EditText editText;

    public CustomLayout(Context context, @Nullable AttributeSet attr) {
        super(context, attr);

        View layout = LayoutInflater.from(context).inflate(R.layout.custom_layout, this); // need to be inflate!!!
        viewText = (TextView) layout.findViewById(R.id.textView);
        editText = (EditText) layout.findViewById(R.id.editText);
        Button button = (Button) layout.findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            viewText.setText(editText.getText());
            editText.setText(null);
        }
    }
}
