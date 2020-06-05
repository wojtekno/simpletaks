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

import timber.log.Timber;

public class GetAllTasksUseCase {

    private LiveData<List<TaskViewData>> allTasksLD;// = new MutableLiveData<>(generateDummyData());

    public GetAllTasksUseCase(TaskRepository repository) {
        Timber.d("GetAllTasksUseCase::newInstance");
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


    //todo delete
    private List<TaskViewData> generateDummyData() {
        List<TaskViewData> mList = new ArrayList<>();

        int id = 0;
        for (int c = 65; c < 69; c++) {
            for (int i = 1; i < 8; i++) {
                char ch = (char) c;
                String taskName = String.format("Task %s%d", ch, i);
                mList.add(new TaskViewData(id, taskName, TaskStatus.OPEN));
                id++;
            }
        }
        return mList;
    }


}
