package com.example.playground.room.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/*
 * Entity recognizable for Room
 * */
@Entity(tableName = "word_table")
public class Word {

    // generate unique key
//    @PrimaryKey(autoGenerate = true)
//    private int id;

    @PrimaryKey
    @NonNull
    // NonNull field must be initialized // Denotes that a parameter, field, or method return value can never be null.
    @ColumnInfo(name = "word")
    private String mWord;

    public Word(@NonNull String word) { // specify table column name
        this.mWord = word;
    }

    // very field that's stored in the database needs to be either public or have a "getter" method.
    public String getWord() {
        return this.mWord;
    }
}
