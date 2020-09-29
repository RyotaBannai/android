package com.example.playground;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.webkit.WebView;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;


import androidx.annotation.Nullable;

public class WebViewActivity extends Activity implements OnClickListener {
    private final int MP = ViewGroup.LayoutParams.MATCH_PARENT;
    private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
    private Button buttonBack;
    private Button buttonForward;
    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // back button
        buttonBack = new Button(this);
        buttonBack.setText("Back");
        buttonBack.setOnClickListener(this);

        // forward button
        buttonForward = new Button(this);
        buttonForward.setText("Go");
        buttonForward.setOnClickListener(this);

        webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://blog.oukasoft.com/%E3%83%97%E3%83%AD%E3%82%B0%E3%83%A9%E3%83%A0/%E3%80%90android%E3%80%91webview%E3%82%92%E4%BD%BF%E3%81%A3%E3%81%A6%E3%82%A2%E3%83%97%E3%83%AA%E5%86%85%E3%81%A7web%E3%83%96%E3%83%A9%E3%82%A6%E3%82%B6%E3%82%92%E5%88%A9%E7%94%A8%E3%81%97%E3%81%A6/");

        LinearLayout btnlinearLayout = new LinearLayout(this);
        btnlinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        btnlinearLayout.addView(buttonBack, createParam(WC, WC));
        btnlinearLayout.addView(buttonForward, createParam(WC, WC));

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(btnlinearLayout, createParam(MP, WC));
        linearLayout.addView(webView, createParam(MP, MP));
        setContentView(linearLayout);
    }

    public void onClick(View v) {
        if (v == buttonBack && webView.canGoBack()) {
            webView.goBack();
        } else if (v == buttonBack && webView.canGoForward()) {
            webView.goForward();
        }
    }

    private LinearLayout.LayoutParams createParam(int w, int h) {
        return new LinearLayout.LayoutParams(w, h);
    }
}
