package com.example.playground.room.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.playground.room.db.dao.WordDao;
import com.example.playground.room.db.entity.Word;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * When you modify the database schema, you'll need to update the version number and define a migration strategy
 * if you don't want to keep history of versions, then set exportSchema = false. default is true;
 * */
@Database(entities = {Word.class}, version = 1, exportSchema = true)
public abstract class WordRoomDatabase extends RoomDatabase {
    /*
     * The database exposes DAOs through an abstract "getter" method for each @Dao.
     * */
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
                            "word_database")
//                            .addMigrations(MIGRATION_1_2)
                            /*
                             * .fallbackToDestructiveMigrationFrom(1, 2) // 1から2と2から3にマイグレーションするときに Database が再生成
                             * .addMigrations(MIGRATION_3_4)             // 3から4のときは通常のマイグレーション
                             *
                             * */
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /*
     * You will add data in two ways:
     * - Add some data when the database is opened, and
     * - add an Activity for adding words.
     * */

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) { // run when database  is opened
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                WordDao dao = INSTANCE.wordDao();
                dao.deleteAll();

                // Populate the database in the background.
                // If you want to start with more words, just add them.
                Word word = new Word("Hello");
                dao.insert(word);
                word = new Word("World");
                dao.insert(word);
            });
        }
    };

    /*
     * Migration code
     * ref:
     * understanding migration: https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929
     * @TypeConverter annotation: https://android.jlelse.eu/5-steps-to-implement-room-persistence-library-in-android-47b10cd47b24
     * https://qiita.com/b_a_a_d_o/items/45bda89f49bf163144af
     * https://medium.com/@star_zero/room%E3%81%AE%E3%83%9E%E3%82%A4%E3%82%B0%E3%83%AC%E3%83%BC%E3%82%B7%E3%83%A7%E3%83%B3%E3%81%BE%E3%81%A8%E3%82%81-a07593aa7c78
     * */
    static final Migration MIGRATION_1_2 = new Migration(1, 2) { // version 1 から 2 になる時に適用される
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `word_table` (`word` TEXT NOT NULL, PRIMARY KEY(`word`))"); // 実際の SQL の中身は 2.json の内容を
        }
    };
}
