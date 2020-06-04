package com.gmail.nowak.wjw.simpletasks.data.db;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.gmail.nowak.wjw.simpletasks.R;
import com.gmail.nowak.wjw.simpletasks.data.local.TaskEntity;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

@Database(entities = TaskEntity.class, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "tasks_db";
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(final Application applicationContext) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Timber.d("Creating new database instance");
                sInstance = Room.databaseBuilder(applicationContext.getApplicationContext(),
                        AppDatabase.class, DATABASE_NAME)
                        .addCallback(new Callback() {
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                super.onCreate(db);
                                Timber.d("RoomDatabase::onCreate(db)");
                                Runnable runnable = new Runnable() {
                                    @Override
                                    public void run() {
                                        Timber.d("PREPOPULATE DATABASE");
                                        List<TaskEntity> entities = generateDummyEntries(applicationContext);
                                        getInstance(applicationContext).taskDao().insertAll(entities);
                                    }
                                };
                                new Thread(runnable).start();
                            }
                        })
                        .build();
            }
        }
        Timber.d("Getting the database instance");
        return sInstance;
    }

    private static List<TaskEntity> generateDummyEntries(Application applicationContext) {
        List<TaskEntity> entities = new ArrayList<>();
        String[] dummyStrings = applicationContext.getResources().getStringArray(R.array.source_dummy_data);
        for(String string :dummyStrings){
            entities.add(new TaskEntity(string));
        }
        return entities;
    }

    public abstract TaskDao taskDao();
}
