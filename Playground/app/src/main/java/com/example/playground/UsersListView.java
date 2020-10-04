package com.example.playground;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ListView;

import com.example.playground.adapter.DataBindingAdapter;
import com.example.playground.data.User;

import java.util.ArrayList;
import java.util.List;

public class UsersListView extends ListView {
    DataBindingAdapter adapter;

    public UsersListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setList(List<User> users) {
        Log.d("DEBUG", "setList is called!");
        if (getAdapter() == null) {
            adapter = new DataBindingAdapter(getContext(), (ArrayList<User>) users);
            setAdapter(adapter);
        }
    }
}
