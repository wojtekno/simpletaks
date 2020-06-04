package com.gmail.nowak.wjw.simpletasks;

import android.app.Application;

import com.gmail.nowak.wjw.simpletasks.data.db.AppDatabase;
import com.gmail.nowak.wjw.simpletasks.data.repository.TaskRepository;
import com.gmail.nowak.wjw.simpletasks.domain.GetAllTasksUseCase;
import com.gmail.nowak.wjw.simpletasks.presentation.list.model.ListViewModelFactory;

import timber.log.Timber;

public class AppGraph {

    private AppDatabase appDatabase;// = AppDatabase.getInstance()
    private TaskRepository taskRepository;// = new TaskRepository();
    private GetAllTasksUseCase getAllTasksUseCase;// = new GetAllTasksUseCase(taskRepository);
    public ListViewModelFactory listViewModelFactory;// = new ListViewModelFactory(getAllTasksUseCase());


    public AppGraph(Application application) {
        appDatabase = AppDatabase.getInstance(application);
        taskRepository =new TaskRepository(appDatabase);
        getAllTasksUseCase = new GetAllTasksUseCase(taskRepository);
        listViewModelFactory = new ListViewModelFactory(getAllTasksUseCase);
        int i = 0;
    }


    //debugging method todo delete
    private GetAllTasksUseCase prepareUseCase() {
        Timber.d("prepareUseCase");
        return new GetAllTasksUseCase(taskRepository);
    }

    //debugging method todo delete
    private ListViewModelFactory prepareFactory() {
        Timber.d("prepareFactory");
        return new ListViewModelFactory(prepareUseCase());

    }
}
