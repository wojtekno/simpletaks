package com.gmail.nowak.wjw.simpletasks.presentation.list.model;

import com.gmail.nowak.wjw.simpletasks.domain.GetAllTasksUseCase;

import timber.log.Timber;

public class ListViewModelFactory_Factory {

    private GetAllTasksUseCase useCase;

    public ListViewModelFactory_Factory(GetAllTasksUseCase useCase) {
        Timber.d("ListViewModelFactory_Factory::newInstance");
        this.useCase = useCase;
    }

    public ListViewModelFactory create(int savedPosition, String savedStatus){
        return new ListViewModelFactory(useCase, savedPosition, savedStatus);
    }
}
