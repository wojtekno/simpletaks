package com.gmail.nowak.wjw.simpletasks;

import android.app.Application;

import com.gmail.nowak.wjw.simpletasks.data.db.AppDatabase;
import com.gmail.nowak.wjw.simpletasks.data.repository.TaskRepository;
import com.gmail.nowak.wjw.simpletasks.domain.GetAllTasksUseCase;
import com.gmail.nowak.wjw.simpletasks.presentation.list.model.ListViewModelFactory_Factory;

import timber.log.Timber;

public class AppGraph {

    private AppDatabase appDatabase;// = AppDatabase.getInstance()
    private TaskRepository taskRepository;// = new TaskRepository();
    private GetAllTasksUseCase getAllTasksUseCase;// = new GetAllTasksUseCase(taskRepository);
    //    public ListViewModelFactory listViewModelFactory;// = new ListViewModelFactory(getAllTasksUseCase());
    public ListViewModelFactory_Factory listViewModelFactory_factory;

    public AppGraph(Application application) {
        appDatabase = AppDatabase.getInstance(application);
        taskRepository = new TaskRepository(appDatabase);
        getAllTasksUseCase = new GetAllTasksUseCase(taskRepository);
//        listViewModelFactory = new ListViewModelFactory(getAllTasksUseCase);
        listViewModelFactory_factory = new ListViewModelFactory_Factory(getAllTasksUseCase);
        int i = 0;
    }


    //debugging method todo delete
    private GetAllTasksUseCase prepareUseCase() {
        Timber.d("prepareUseCase");
        return new GetAllTasksUseCase(taskRepository);
    }

    //debugging method todo delete
    private ListViewModelFactory_Factory prepareListViewModelFactory_Factory() {
        Timber.d("prepareFactory");
        return new ListViewModelFactory_Factory(prepareUseCase());

    }
}
