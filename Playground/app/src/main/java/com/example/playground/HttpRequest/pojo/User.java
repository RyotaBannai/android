package com.example.playground.HttpRequest.pojo;

import com.google.gson.annotations.SerializedName;

/*
 * used to create the Response Body for the createUser() method
 * */
public class User {
    @SerializedName("name")
    public String name;
    @SerializedName("job")
    public String job;
    @SerializedName("id")
    public String id;
    @SerializedName("createdAt")
    public String createAt;

    public User(String name, String job) {
        this.name = name;
        this.job = job;
    }
}
