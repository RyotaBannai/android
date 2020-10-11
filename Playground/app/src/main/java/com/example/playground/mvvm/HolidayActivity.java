package com.example.playground.mvvm;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.playground.R;
import com.example.playground.databinding.MvvmHolidayActivityBinding;

import java.util.List;

public class HolidayActivity extends AppCompatActivity {
    final String TAG = getClass().getSimpleName();
    MvvmHolidayActivityBinding binding;
    HolidayAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.mvvm_holiday_activity);
        initUI();

        if (true) {
            binding.progressBar.setVisibility(View.VISIBLE);
            HolidayViewModel holidayViewModel = new HolidayViewModel();
            holidayViewModel.getHolidays().observe(this, new Observer<List<HolidayModel>>() {
                @Override
                public void onChanged(List<HolidayModel> holidayModels) {
                    if (holidayModels != null && !holidayModels.isEmpty()) {
                        Log.d(TAG, "observe onChanged()=" + holidayModels.size());
                        binding.progressBar.setVisibility(View.GONE);
                        adapter.addHolidayList(holidayModels);
                        adapter.notifyDataSetChanged();
                    }
                }
            });
        } else {
            Toast.makeText(this, "No Network Available", Toast.LENGTH_SHORT).show();
        }
    }

    void initUI() {
        binding.rvHolidayList.setHasFixedSize(true);
        binding.rvHolidayList.setLayoutManager(new LinearLayoutManager(this));

        adapter = new HolidayAdapter();
        binding.rvHolidayList.setAdapter(adapter);
    }
}
