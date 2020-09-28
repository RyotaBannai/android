package com.example.playground;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.View.OnClickListener;
import android.text.SpannableStringBuilder;


import androidx.annotation.Nullable;

public class EditActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(linearLayout);

        final EditText et = new EditText(this);
        et.setText("Hello"); // set default value
        linearLayout.addView(et, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        SpannableStringBuilder sb = (SpannableStringBuilder) et.getText();
        String str = sb.toString();

        Button button = new Button(this);
        button.setText("get text");
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                new AlertDialog.Builder(EditActivity.this)
                        .setTitle("The value is:")
                        .setMessage(et.getText().toString())
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete operation
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();
            }
        });
        linearLayout.addView(button,
                new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
    }
}
