package com.example.playground;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.databinding.DataBindingUtil;

import com.example.playground.data.User2;
import com.example.playground.databinding.ViewUserInfoBinding;

import javax.annotation.Nullable;

public class UserInfoView extends LinearLayout {
    ViewUserInfoBinding mBinding;

//    public UserInfoView(Context context) {
//        super(context);
//    }

    public UserInfoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = LayoutInflater.from(context);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.view_user_info, this, false); // being forced to set value from/ by layout
    }

    public void setUser2(User2 user2) { // being forced to use 'setUser2' because layout file requires from/ in <variable> tag
        mBinding.setUser2(user2);
    }
}
