package com.gmail.nowak.wjw.simpletasks.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.gmail.nowak.wjw.simpletasks.data.local.TaskEntity;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task")
    LiveData<List<TaskEntity>> getAllTasks();

    @Insert
    void insertAll(List<TaskEntity> entities);
}
