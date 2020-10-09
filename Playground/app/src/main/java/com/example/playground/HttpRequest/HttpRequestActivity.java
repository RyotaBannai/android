package com.example.playground.HttpRequest;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.playground.R;

public class HttpRequestActivity extends AppCompatActivity {

    TextView responseText;
    APIInterface apiInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.http_request_activity_main);
        responseText = (TextView) findViewById(R.id.responseText);
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }
}
