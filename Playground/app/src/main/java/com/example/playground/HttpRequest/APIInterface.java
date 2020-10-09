package com.example.playground.HttpRequest;

import com.example.playground.HttpRequest.pojo.MultipleResources;
import com.example.playground.HttpRequest.pojo.User;
import com.example.playground.HttpRequest.pojo.UserList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

interface APIInterface {
    @GET("/api/unknown")
    Call<MultipleResources> doGetListResources();

    @POST("/api/users")
    Call<User> createUser(@Body User user);

    @GET("/api/users?")
    Call<UserList> doGetUserList(@Query("page") String page);

    @FormUrlEncoded
    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);

}
