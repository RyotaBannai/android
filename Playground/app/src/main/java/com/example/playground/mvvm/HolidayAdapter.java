package com.example.playground.mvvm;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playground.databinding.ItemHolidayBinding;
import com.example.playground.databinding.MvvmHolidayActivityBinding;

import java.util.ArrayList;
import java.util.List;

import com.example.playground.R;

class HolidayAdapter extends RecyclerView.Adapter<HolidayAdapter.MyViewHolder> {

    private List<HolidayModel> holidayList;

    public HolidayAdapter() {
        holidayList = new ArrayList<>();
    }

    public void addHolidayList(List<HolidayModel> currentList) {
        this.holidayList = currentList;
    }

    @Override
    public int getItemCount() {
        return holidayList != null ? holidayList.size() : 0;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHolidayBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_holiday,
                parent,
                false
        );
        return new MyViewHolder(binding);
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        ItemHolidayBinding binding;

        MyViewHolder(ItemHolidayBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.binding.setModel(holidayList.get(position));
    }
}
