package com.gmail.nowak.wjw.simpletasks.domain;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gmail.nowak.wjw.simpletasks.data.model.TaskStatus;
import com.gmail.nowak.wjw.simpletasks.data.model.TaskViewData;

import java.util.ArrayList;
import java.util.List;

public class GetAllTasksUseCase {

    private LiveData<List<TaskViewData>> allTasksLD = new MutableLiveData<>(generateDummyData());

    public GetAllTasksUseCase(){
    }

    public LiveData<List<TaskViewData>> getAllTasks(){
        return allTasksLD;
    }



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

    private List<String> generateStringDummyData() {
        List<String> mList = new ArrayList<>();

        int id = 0;
        for (int c = 65; c < 69; c++) {
            for (int i = 1; i < 8; i++) {
                char ch = (char) c;
                String taskName = String.format("Task %s%d", ch, i);
                mList.add(taskName);
                id++;
            }
        }
        return mList;
    }
}
