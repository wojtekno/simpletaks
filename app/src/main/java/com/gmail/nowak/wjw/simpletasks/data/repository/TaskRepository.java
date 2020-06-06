package com.gmail.nowak.wjw.simpletasks.data.repository;

import androidx.lifecycle.LiveData;

import com.gmail.nowak.wjw.simpletasks.data.db.AppDatabase;
import com.gmail.nowak.wjw.simpletasks.data.local.TaskEntity;

import java.util.List;

public class TaskRepository {
    private AppDatabase db;

    public TaskRepository(AppDatabase db) {
        this.db = db;
    }

    public LiveData<List<TaskEntity>> getAllTasks() {
        return db.taskDao().getAllTasks();
    }

}
