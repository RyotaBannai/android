package com.example.playground.mvvm;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class HolidayRepository {

    private final String TAG = getClass().getSimpleName();

    public MutableLiveData<List<HolidayModel>> requestHolidays() {
        final MutableLiveData<List<HolidayModel>> mutableLiveData = new MutableLiveData<>();
        APIInterface apiInterface = MyApplication.getClient().create(APIInterface.class);
        apiInterface.getHolidays().enqueue(new Callback<List<HolidayModel>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<HolidayModel>> call, Response<List<HolidayModel>> response) {
                Log.e(TAG, "getCurrencyList response=" + response);

                if (response.isSuccessful() && response.body() != null) {
                    Log.e(TAG, "requestHolidays response.size=" + response.body());
                    mutableLiveData.setValue(response.body());
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<List<HolidayModel>> call, Throwable t) {
                Log.e(TAG, "getProdList onFailure" + call.toString());
                call.cancel();
            }
        });

        return mutableLiveData;
    }
}
