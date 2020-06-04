package com.gmail.nowak.wjw.simpletasks.presentation.list.model;

import android.content.res.Resources;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.gmail.nowak.wjw.simpletasks.R;
import com.gmail.nowak.wjw.simpletasks.data.model.TaskStatus;
import com.gmail.nowak.wjw.simpletasks.data.model.TaskViewData;
import com.gmail.nowak.wjw.simpletasks.domain.GetAllTasksUseCase;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class TaskListViewModel extends ViewModel {


    private MutableLiveData<List<TaskViewData>> tasksLD;// = new MutableLiveData<>();
    private boolean isTaskInProgress;

    public TaskListViewModel(GetAllTasksUseCase getAllTasksUseCase) {
        Timber.d("TaskListViewModel::newInstance");
        tasksLD = (MutableLiveData<List<TaskViewData>>) getAllTasksUseCase.getAllTasks();
    }

    public LiveData<List<TaskViewData>> getTasksLD() {
        return tasksLD;
    }

    public boolean changeTaskStatus(int listPosition) {
        //Create a list and a task with a new reference. Otherwise we would modify at the same time a list held in TaskListAdapter.
        //That in turn wouldn't trigger DiffUtil.ItemCallback
        List<TaskViewData> taskList = new ArrayList<>(tasksLD.getValue());
        TaskViewData task = new TaskViewData(taskList.get(listPosition));
        switch (task.getStatus()) {
            case OPEN:
                if (isTaskInProgress) {
                    return false;
                }
                task.setStatus(TaskStatus.TRAVELLING);
                isTaskInProgress = true;
                break;
            case TRAVELLING:
                task.setStatus(TaskStatus.WORKING);
                break;
            case WORKING:
                task.setStatus(TaskStatus.OPEN);
                isTaskInProgress = false;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + task.getStatus());
        }
        taskList.set(listPosition, task);
        tasksLD.setValue(taskList);
        return true;
    }
}
