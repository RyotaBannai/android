package com.example.playground.mvvm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class HolidayViewModel extends ViewModel {

    private HolidayRepository holidayRepository;
    private MutableLiveData<List<HolidayModel>> mutableLiveData;

    public HolidayViewModel() {
        holidayRepository = new HolidayRepository();
    }

    public LiveData<List<HolidayModel>> getHolidays() {
        if (mutableLiveData == null) {
            mutableLiveData = holidayRepository.requestHolidays();
        }
        return mutableLiveData;
    }
}
