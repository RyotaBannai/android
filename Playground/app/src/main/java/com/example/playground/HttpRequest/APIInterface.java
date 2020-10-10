package com.example.playground.HttpRequest;

import com.example.playground.HttpRequest.pojo.MultipleResources;
import com.example.playground.HttpRequest.pojo.User;
import com.example.playground.HttpRequest.pojo.UserList;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

interface APIInterface {
    /*
     * sorting
     * @GET("group/{id}/users/list")
     * Call<List<User>> groupList(@Path("id") int groupId, @Query("sort") String sort);
     *
     * building query
     * @GET("group/{id}/users/list")
     * Call<List<User>> groupList(@Path("id") int groupId, @QueryMap Map<String, String> options);
     * => list?key=value
     * */
    @GET("/api/unknown")
//    Call<MultipleResources> doGetListResources(@QueryMap Map<String, Integer> options);
    Call<MultipleResources> doGetListResources(@Query("page") Integer page);

    @POST("/api/users")
    Call<User> createUser(@Body User user);

    @GET("/api/users?")
    Call<UserList> doGetUserList(@Query("page") String page);

    @FormUrlEncoded
    @POST("/api/users?")
    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);

}
