package com.example.playground.room.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.playground.room.db.dao.WordDao;
import com.example.playground.room.db.entity.Word;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * When you modify the database schema, you'll need to update the version number and define a migration strategy
 * */
@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {
    public abstract WordDao wordDao();

    private static volatile WordRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS); // you will use to run database operations asynchronously on a background thread.

    public static WordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            WordRoomDatabase.class,
                            "word_database"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}
