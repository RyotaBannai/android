package com.example.playground.HttpRequest;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.playground.HttpRequest.pojo.MultipleResources;
import com.example.playground.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class HttpRequestActivity extends AppCompatActivity {

    TextView responseText;
    APIInterface apiInterface;

    String Tag = getClass().getSimpleName();
    Map options = new HashMap<String, Integer>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.http_request_activity_main);
        responseText = (TextView) findViewById(R.id.responseText);

        // instantiate the APIClient
        apiInterface = APIClient.getClient().create(APIInterface.class);

        /*
         * GET List Resources
         * */
        options.put("page", 2);
        Call<MultipleResources> call = apiInterface.doGetListResources(options);
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
                Integer total = resources.total;
                Integer totalPages = resources.totalPages;
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
