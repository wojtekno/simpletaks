package com.gmail.nowak.wjw.simpletasks;

import com.gmail.nowak.wjw.simpletasks.domain.GetAllTasksUseCase;
import com.gmail.nowak.wjw.simpletasks.presentation.list.model.ListViewModelFactory;

import timber.log.Timber;

public class AppGraph {

    public GetAllTasksUseCase getAllTasksUseCase = new GetAllTasksUseCase();
    public ListViewModelFactory listViewModelFactory = new ListViewModelFactory(getAllTasksUseCase);



    //debugging method todo delete
    private GetAllTasksUseCase prepareUseCase() {
        Timber.d("prepareUseCase");
        return new GetAllTasksUseCase();
    }

    //debugging method todo delete
    private ListViewModelFactory prepareFactory() {
        Timber.d("prepareFactory");
        return new ListViewModelFactory(getAllTasksUseCase);

    }
}
