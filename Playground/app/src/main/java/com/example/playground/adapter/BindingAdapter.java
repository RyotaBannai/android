package com.example.playground.adapter;

import android.widget.Adapter;

import com.example.playground.UserInfoView;
import com.example.playground.data.User2;

public class BindingAdapter {
    @androidx.databinding.BindingAdapter("user_data")
    public static void setUser2(UserInfoView view, User2 user2) {
        view.setUser2(user2);
    }
}
