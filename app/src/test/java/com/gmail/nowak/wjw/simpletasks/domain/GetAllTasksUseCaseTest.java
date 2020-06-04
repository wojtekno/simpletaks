package com.gmail.nowak.wjw.simpletasks.domain;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.gmail.nowak.wjw.simpletasks.data.local.TaskEntity;
import com.gmail.nowak.wjw.simpletasks.data.model.TaskViewData;
import com.gmail.nowak.wjw.simpletasks.data.repository.TaskRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class GetAllTasksUseCaseTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    TaskRepository repository;
    @Mock
    Observer<List<TaskViewData>> observer;
    GetAllTasksUseCase useCase;

    @Before
    public void setUp() throws Exception {
        when(repository.getAllTasks()).thenReturn(new MutableLiveData<List<TaskEntity>>(generateDummyData()));
        useCase = new GetAllTasksUseCase(repository);
        useCase.getAllTasks().observeForever(observer);
    }

    @Test
    public void getAllTasks() {
        List<TaskViewData> list = useCase.getAllTasks().getValue();
        assertEquals(40, list.size());
    }

    private List<TaskEntity> generateDummyData() {
        List<TaskEntity> mList = new ArrayList<>();

        int id = 0;
        for (int c = 65; c < 69; c++) {
            for (int i = 0; i < 10; i++) {
                char ch = (char) c;
                String taskName = String.format("Task %s%d", ch, i);
                mList.add(new TaskEntity(id, taskName));
            }
        }
        return mList;
    }
}