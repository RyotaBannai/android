package com.example.playground.room.db.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.playground.room.db.entity.Word;
import com.example.playground.room.db.repository.WordRepository;

import java.util.List;

/*
 * If you need the application context (which has a lifecycle that lives as long as the application does),
 * use AndroidViewModel
 * */

public class WordViewModel extends AndroidViewModel {
    /*
     * Also you can hold local data without caring UI Controller's life cycle
     * ref: https://medium.com/androiddevelopers/viewmodels-a-simple-example-ed5ac416317e
     * */
    private WordRepository mRepository;

    private LiveData<List<Word>> mAllWords;

    public WordViewModel(Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getmAllWords();
    }

    public LiveData<List<Word>> getmAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        mRepository.insert(word);
    }

}
