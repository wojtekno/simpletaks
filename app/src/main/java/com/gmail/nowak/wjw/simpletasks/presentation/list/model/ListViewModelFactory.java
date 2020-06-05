package com.gmail.nowak.wjw.simpletasks.presentation.list.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.gmail.nowak.wjw.simpletasks.domain.GetAllTasksUseCase;

import timber.log.Timber;

public class ListViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private GetAllTasksUseCase useCase;
    private int savedPosition;
    private String savedStatus;

    public ListViewModelFactory(GetAllTasksUseCase useCase, int savedPosition, String savedStatus) {
        Timber.d("ListViewModelFactory::newInstance");
        this.useCase = useCase;
        this.savedPosition = savedPosition;
        this.savedStatus = savedStatus;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new TaskListViewModel(useCase, savedPosition, savedStatus);
    }
}
