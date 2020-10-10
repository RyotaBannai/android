package com.example.playground.HttpRequest;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.playground.HttpRequest.pojo.MultipleResources;
import com.example.playground.HttpRequest.pojo.User;
import com.example.playground.HttpRequest.pojo.UserList;
import com.example.playground.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

/*
 * reference: https://www.journaldev.com/13639/retrofit-android-example-tutorial#retrofit-android
 * */

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

        // test
        User user = new User("Morpheus", "leader");
        createUser(user);
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

                displayResponse.append(text).append(" Page\n")
                        .append(total).append(" Total\n")
                        .append(totalPages).append(" Total Pages\n");
                for (MultipleResources.Datum datum : datumList) {
                    displayResponse.append(datum.id).append(" ")
                            .append(datum.name).append(" ")
                            .append(datum.pantoneValue).append(" ")
                            .append(datum.year).append("\n");
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

    private void createUser(User user) {
        Call<User> call = apiInterface.createUser(user);
        call.enqueue(new Callback<User>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                assert user != null;
                Toast.makeText(getApplicationContext(), user.name + " " + user.job + " " + user.id + " " + user.createAt, Toast.LENGTH_SHORT).show();
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                call.cancel();
            }
        });
    }

    private void createUserFromForm(String name, String job) {
        Call<UserList> call3 = apiInterface.doCreateUserWithField(name, job);
        call3.enqueue(new Callback<UserList>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                UserList userList = response.body();
                assert userList != null;
                Integer text = userList.page;
                Integer total = userList.total;
                Integer totalPages = userList.totalPages;
                List<UserList.Datum> datumList = userList.data;
                Toast.makeText(getApplicationContext(), text + " page\n" + total + " total\n" + totalPages + " totalPages\n", Toast.LENGTH_SHORT).show();

                for (UserList.Datum datum : datumList) {
                    Toast.makeText(getApplicationContext(), "id : " + datum.id + " name: " + datum.first_name + " " + datum.last_name + " avatar: " + datum.avatar, Toast.LENGTH_SHORT).show();
                }

            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                call.cancel();
            }
        });
    }
}
