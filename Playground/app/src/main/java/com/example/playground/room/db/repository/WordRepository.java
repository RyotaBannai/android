package com.example.playground.room.db.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.playground.room.db.WordRoomDatabase;
import com.example.playground.room.db.dao.WordDao;
import com.example.playground.room.db.entity.Word;

import java.util.List;

/*
 * Room executes all queries on a separate thread.
 * Then observed LiveData will notify the observer on the main thread when the data has changed.
 * */
public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao(); // abstract getter method
        mAllWords = mWordDao.getAlphabetizedWords();
    }

    public LiveData<List<Word>> getmAllWords() {
        return mAllWords;
    }

    /*
     * the insert on a background thread.
     * */
    public void insert(Word word) {
        WordRoomDatabase.databaseWriteExecutor.execute(() -> {
            mWordDao.insert(word);
        });
    }
}
