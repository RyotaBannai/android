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
    private WordRepository mRepository;

    private LiveData<List<Word>> mAllWords;

    public WordViewModel(Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getmAllWords();
    }

    LiveData<List<Word>> getmAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        mRepository.insert(word);
    }

}
