package com.example.playground.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.example.playground.R;
import com.example.playground.ViewHolder;
import com.example.playground.data.User;
import com.example.playground.databinding.BindingMainActivityBinding;

import java.util.ArrayList;

public class DataBindingAdapter extends ArrayAdapter<User> {
//    ViewHolder holder = null;

    public DataBindingAdapter(Context context, ArrayList<User> users) {
        super(context, 0, users);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);

        /*
         * View との紐づけに Data Binding を用いることで View Holder が不要になりスッキリ書ける.
         * */
        BindingMainActivityBinding binding;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            binding = DataBindingUtil.inflate(inflater, R.layout.binding_main_activity, parent, false);

            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (BindingMainActivityBinding) convertView.getTag();
        }
        binding.setUser(getItem(position));
        return binding.getRoot();

        /*
         * View Holder を使う場合
         * */
//        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//        if(convertView == null){
//            convertView = inflater.inflate(R.layout.binding_main_activity, null);
//            holder = new ViewHolder(convertView);
//            convertView.setTag(holder);
//        }
//        else {
//            holder = (ViewHolder) convertView.getTag();
//        }
//
//        holder.name = "NAME";
//        holder.age = 10;

    }
}
