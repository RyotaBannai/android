package com.example.playground.HttpRequest;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.playground.HttpRequest.pojo.MultipleResources;
import com.example.playground.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class HttpRequestActivity extends AppCompatActivity implements View.OnClickListener {

    TextView responseText;
    APIInterface apiInterface;
    private Integer _currentPage = 1;
    private Integer _totalPages = 1;

    String Tag = getClass().getSimpleName();

    Button nextButton;
    Button backButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.http_request_activity_main);
        responseText = (TextView) findViewById(R.id.responseText);

        // instantiate the APIClient
        apiInterface = APIClient.getClient().create(APIInterface.class);

        this.getColors(_currentPage);

        // add button
        nextButton = findViewById(R.id.next);
        nextButton.setOnClickListener(this);
        backButton = findViewById(R.id.back);
        backButton.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        if (v == nextButton && _currentPage < _totalPages) {
            this.getColors(++_currentPage);
        } else if (v == backButton && _currentPage > 1) {
            this.getColors(--_currentPage);
        }
    }

    /*
     * GET List Resources
     * */
    private void getColors(Integer page) {
        Call<MultipleResources> call = apiInterface.doGetListResources(page);
        /*
         * Asynchronously send the request and notify callback of its response or if an error occurred talking to the server,
         * creating the request, or processing the response.
         * */
        call.enqueue(new Callback<MultipleResources>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<MultipleResources> call, Response<MultipleResources> response) {
                Log.d(Tag, response.code() + "");

                StringBuilder displayResponse = new StringBuilder();
                MultipleResources resources = response.body();
                Integer text = resources.page;
                _currentPage = resources.page;
                Integer total = resources.total;
                Integer totalPages = resources.totalPages;
                _totalPages = resources.totalPages;
                List<MultipleResources.Datum> datumList = resources.data;

                displayResponse.append(text).append(" Page \n").append(total).append(" Total\n").append(totalPages).append(" Total Pages\n");
                for (MultipleResources.Datum datum : datumList) {
                    displayResponse.append(datum.id).append(" ").append(datum.name).append(" ").append(datum.pantoneValue).append(" ").append(datum.year).append("\n");
                }

                responseText.setText(displayResponse);
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<MultipleResources> call, Throwable t) {
                call.cancel();
            }
        });
    }
}
