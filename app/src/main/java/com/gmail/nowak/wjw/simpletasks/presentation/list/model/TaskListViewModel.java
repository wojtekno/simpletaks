package com.gmail.nowak.wjw.simpletasks.presentation.list.model;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.gmail.nowak.wjw.simpletasks.data.model.TaskStatus;
import com.gmail.nowak.wjw.simpletasks.data.model.TaskViewData;
import com.gmail.nowak.wjw.simpletasks.domain.GetAllTasksUseCase;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class TaskListViewModel extends ViewModel {


    private MutableLiveData<List<TaskViewData>> tasksLD;// = new MutableLiveData<>();
    private boolean isAnyTaskInProgress;

    //todo delete
    public TaskListViewModel(GetAllTasksUseCase getAllTasksUseCase) {
        Timber.d("TaskListViewModel::newInstance");
        tasksLD = (MutableLiveData<List<TaskViewData>>) getAllTasksUseCase.getAllTasks();
    }

    public TaskListViewModel(GetAllTasksUseCase getAllTasksUseCase, final int savedPosition, final String taskStatus) {
        Timber.d("TaskListViewModel::newInstance(%d, %s)", savedPosition, taskStatus);
        if (savedPosition == -1) {
            tasksLD = (MutableLiveData<List<TaskViewData>>) getAllTasksUseCase.getAllTasks();
        } else {
            tasksLD = (MutableLiveData<List<TaskViewData>>) Transformations.map(getAllTasksUseCase.getAllTasks(), new Function<List<TaskViewData>, List<TaskViewData>>() {
                @Override
                public List<TaskViewData> apply(List<TaskViewData> input) {
                    input.get(savedPosition).setStatus(TaskStatus.OPEN.valueOf(taskStatus));
                    isAnyTaskInProgress = true;
                    return input;
                }
            });
        }
        Timber.d("DONE TaskListViewModel::newInstance(%d, %s)", savedPosition, taskStatus);
    }


    public LiveData<List<TaskViewData>> getTasksLD() {
        return tasksLD;
    }

    /**
     * Handles changing status of a Task stored in TasksLD.
     * If there is already a task with status other then OPEN, it doesn't allow for another change.
     * Only one task at a time can have status other than OPEN
     * @param listPosition position in the list of task to change
     * @return true if changed made, false otherwise
     */
    public boolean changeTaskStatus(int listPosition) {
        //Create a list and a task with a new reference. Otherwise we would modify at the same time a list held in TaskListAdapter.
        //That in turn wouldn't trigger DiffUtil.ItemCallback
        List<TaskViewData> taskList = new ArrayList<>(tasksLD.getValue());
        TaskViewData task = new TaskViewData(taskList.get(listPosition));
        switch (task.getStatus()) {
            case OPEN:
                if (isAnyTaskInProgress) {
                    return false;
                }
                task.setStatus(TaskStatus.TRAVELLING);
                isAnyTaskInProgress = true;
                break;
            case TRAVELLING:
                task.setStatus(TaskStatus.WORKING);
                break;
            case WORKING:
                task.setStatus(TaskStatus.OPEN);
                isAnyTaskInProgress = false;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + task.getStatus());
        }
        taskList.set(listPosition, task);
        tasksLD.setValue(taskList);
        return true;
    }

    public boolean isAnyTaskInProgress() {
        return isAnyTaskInProgress;
    }
}
