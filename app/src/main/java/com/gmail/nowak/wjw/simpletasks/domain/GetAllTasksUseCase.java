package com.gmail.nowak.wjw.simpletasks.domain;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.gmail.nowak.wjw.simpletasks.data.local.TaskEntity;
import com.gmail.nowak.wjw.simpletasks.data.model.TaskStatus;
import com.gmail.nowak.wjw.simpletasks.data.model.TaskViewData;
import com.gmail.nowak.wjw.simpletasks.data.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;

public class GetAllTasksUseCase {

    private LiveData<List<TaskViewData>> allTasksLD;

    public GetAllTasksUseCase(TaskRepository repository) {
        allTasksLD = Transformations.map(repository.getAllTasks(), transform);
    }

    Function<List<TaskEntity>, List<TaskViewData>> transform = new Function<List<TaskEntity>, List<TaskViewData>>() {
        @Override
        public List<TaskViewData> apply(List<TaskEntity> input) {
            List<TaskViewData> tasks = new ArrayList<>();
            for (TaskEntity taskEntity : input) {
                tasks.add(new TaskViewData(taskEntity.getId(), taskEntity.getName(), TaskStatus.OPEN));
            }
            return tasks;
        }
    };

    public LiveData<List<TaskViewData>> getAllTasks() {
        return allTasksLD;
    }

}
