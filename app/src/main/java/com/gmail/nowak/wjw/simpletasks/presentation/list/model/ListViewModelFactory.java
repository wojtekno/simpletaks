package com.gmail.nowak.wjw.simpletasks.presentation.list.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.gmail.nowak.wjw.simpletasks.domain.GetAllTasksUseCase;

public class ListViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private GetAllTasksUseCase useCase;

    public ListViewModelFactory(GetAllTasksUseCase useCase) {
        this.useCase = useCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new TaskListViewModel(useCase);
    }
}
