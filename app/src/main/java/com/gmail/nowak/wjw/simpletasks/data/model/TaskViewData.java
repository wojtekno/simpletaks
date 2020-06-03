package com.gmail.nowak.wjw.simpletasks.data.model;

import androidx.annotation.Nullable;

public class TaskViewData {

    private int id;
    private String name;
    private TaskStatus status;

    public TaskViewData(int id, String name, TaskStatus status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public TaskViewData(TaskViewData taskViewData) {
        this.id = taskViewData.id;
        this.name = taskViewData.name;
        this.status = taskViewData.status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TaskStatus getStatus() {
        return status;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result * id;
        result = 31 * result * name.hashCode();
        result = 31 * result * status.hashCode();

        return result;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof TaskViewData)) {
            return false;
        }

        TaskViewData task = (TaskViewData) obj;
        return id == task.id && name.equals(task.name) && status.equals(task.status);
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
