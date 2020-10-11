package com.example.playground.room.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.playground.room.db.entity.Word;

import java.util.List;

/*
 *  DAOs must either be interfaces or abstract classes.
 * */
@Dao
public interface WordDao {
    // allowing the insert of the same word multiple times
    // by passing a conflict resolution strategy
    // The @Insert annotation is a special DAO method annotation where you don't have to provide any SQL!
    // ignores a new word if it's exactly the same as one already in the list
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);

    @Query("DELETE from word_table")
    void deleteAll();

    @Query("SELECT * from word_table ORDER BY word ASC")
    List<Word> getAlphabetizedWords();
}
