package com.example.myproject.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MergerViewModel extends ViewModel {
    MutableLiveData<String> liveData_first_name;
    MutableLiveData<String> liveData_last_name;
    MediatorLiveData<String> liveDataMerger;

    public MutableLiveData<String> getFullName() {
        if (liveData_first_name == null) {
            liveData_first_name = new MutableLiveData<String>("");
        }
        if (liveData_last_name == null) {
            liveData_last_name = new MutableLiveData<String>("");
        }
        if (liveDataMerger == null) {
            liveDataMerger = new MediatorLiveData<>();
            liveDataMerger.addSource(liveData_first_name, value ->
                    liveDataMerger.setValue(
                            value.toString() + " " + liveData_last_name.getValue()
                    )
            );
            liveDataMerger.addSource(liveData_last_name, value ->
                    liveDataMerger.setValue(
                            liveData_first_name.getValue() + " " + value.toString()
                    )
            );
        }
        return liveDataMerger;
    }

    public MutableLiveData<String> getFirstName() {
        return liveData_first_name;
    }

    public MutableLiveData<String> getLastName() {
        return liveData_last_name;
    }
}
