package com.gmail.nowak.wjw.simpletasks.presentation;

import android.content.res.Resources;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gmail.nowak.wjw.simpletasks.R;
import com.gmail.nowak.wjw.simpletasks.data.model.TaskStatus;
import com.gmail.nowak.wjw.simpletasks.data.model.TaskViewData;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class TaskListViewModel extends ViewModel {

    private MutableLiveData<List<TaskViewData>> tasksLD = new MutableLiveData<>();
    private boolean isTaskInProgress;

    public TaskListViewModel() {
        tasksLD.setValue(generateDummyData());
    }


    public LiveData<List<TaskViewData>> getTasksLD() {
        return tasksLD;
    }

    private List<TaskViewData> generateDummyData() {
        List<TaskViewData> mList = new ArrayList<>();

        int id = 0;
        for (int c = 65; c < 69; c++) {
            for (int i = 1; i < 7; i++) {
                char ch = (char) c;
                String taskName = String.format("Task %s%d", ch, i);
                mList.add(new TaskViewData(id, taskName, TaskStatus.OPEN));
                id++;
            }
        }
        return mList;
    }

    public boolean changeTaskStatus(int listPosition) {
        //Create a list and a task with a new reference. Otherwise we would modify at the same time a list held in TaskListAdapter.
        //That in turn wouldn't trigger DiffUtil.ItemCallback
        List<TaskViewData> taskList = new ArrayList<>(tasksLD.getValue());
        TaskViewData task = new TaskViewData(taskList.get(listPosition));
        switch (task.getStatus()) {
            case OPEN:
                if(isTaskInProgress){
                    return false;
                }
                task.setStatus(TaskStatus.TRAVELLING);
                isTaskInProgress=true;
                break;
            case TRAVELLING:
                task.setStatus(TaskStatus.WORKING);
                break;
            case WORKING:
                task.setStatus(TaskStatus.OPEN);
                isTaskInProgress=false;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + task.getStatus());
        }
        taskList.set(listPosition, task);
        tasksLD.setValue(taskList);
        return true;
    }
}
