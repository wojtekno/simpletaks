package com.gmail.nowak.wjw.simpletasks;

import android.app.Application;

import com.gmail.nowak.wjw.simpletasks.data.db.AppDatabase;
import com.gmail.nowak.wjw.simpletasks.data.repository.TaskRepository;
import com.gmail.nowak.wjw.simpletasks.domain.GetAllTasksUseCase;
import com.gmail.nowak.wjw.simpletasks.presentation.list.model.ListViewModelFactory_Factory;

public class AppGraph {

    private AppDatabase appDatabase;
    private TaskRepository taskRepository;
    private GetAllTasksUseCase getAllTasksUseCase;
    public ListViewModelFactory_Factory listViewModelFactory_factory;

    public AppGraph(Application application) {
        appDatabase = AppDatabase.getInstance(application);
        taskRepository = new TaskRepository(appDatabase);
        getAllTasksUseCase = new GetAllTasksUseCase(taskRepository);
        listViewModelFactory_factory = new ListViewModelFactory_Factory(getAllTasksUseCase);
    }
}
