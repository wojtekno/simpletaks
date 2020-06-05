package com.gmail.nowak.wjw.simpletasks.data.repository;

import androidx.lifecycle.LiveData;

import com.gmail.nowak.wjw.simpletasks.data.db.AppDatabase;
import com.gmail.nowak.wjw.simpletasks.data.local.TaskEntity;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class TaskRepository {
    private AppDatabase db;

    public TaskRepository(AppDatabase db) {
        Timber.d("TaskRepository::newInstance(database)");
        this.db = db;
    }

    //debugging method todo delete
    public TaskRepository() {
        Timber.d("TaskRepository::newInstance(empty)");
    }

    public LiveData<List<TaskEntity>> getAllTasks() {
        return db.taskDao().getAllTasks();
    }

    //todo delete
    private List<String> generateStringDummyData() {
        List<String> mList = new ArrayList<>();

        for (int c = 65; c < 69; c++) {
            for (int i = 1; i < 10; i++) {
                char ch = (char) c;
                String taskName = String.format("Task %s%d", ch, i);
                mList.add(taskName);
            }
        }
        return mList;
    }
}
