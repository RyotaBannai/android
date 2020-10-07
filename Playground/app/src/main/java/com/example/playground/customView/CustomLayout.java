package com.example.playground.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/* Add two below module to use styleable */
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.example.playground.R;

import javax.annotation.Nullable;

/*
 * ref: custom layout and custom view http://ojed.hatenablog.com/entry/2015/12/05/161013#layout
 * */
public class CustomLayout extends LinearLayout implements View.OnClickListener {
    private TextView viewText;
    private EditText editText;

    /* Add two below module to use styleable */
    private boolean mEnableToSend;
    private int mButtonName;

    public CustomLayout(Context context, @Nullable AttributeSet attr) {
        super(context, attr);
        View layout = LayoutInflater.from(context).inflate(R.layout.custom_layout, this, true); // need to be inflate!!!

        /* This TypedArray should be recycled after use with #recycle() */
        TypedArray a = context.obtainStyledAttributes(attr, R.styleable.CustomLayout, 0, 0);

        try {
            mEnableToSend = a.getBoolean(R.styleable.CustomLayout_EnableToSend, true);
            mButtonName = a.getInteger(R.styleable.CustomLayout_ButtonName, 0);
        } finally {
            a.recycle();
        }
        Button button = (Button) layout.findViewById(R.id.customButton);
        button.setOnClickListener(this);

        switch (mButtonName) {
            case 0:
                button.setText("send");
                break;
            case 1:
                button.setText("save");
                break;
            case 2:
                button.setText("delete");
                break;
            default:
                break;
        }

        viewText = (TextView) layout.findViewById(R.id.textView);
        editText = (EditText) layout.findViewById(R.id.editText);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.customButton) {
            if (mEnableToSend)
                viewText.setText(editText.getText());
            editText.setText(null);
        }
    }

    /*
     * Add setter to change attrs dynamically.
     * */
    public void setmEnableToSend(Boolean enable) {
        this.mEnableToSend = enable;
    }

    public void setmButtonName(int buttonNameEnum) { // int because attrs are Enum values
        this.mButtonName = buttonNameEnum;
    }
}
