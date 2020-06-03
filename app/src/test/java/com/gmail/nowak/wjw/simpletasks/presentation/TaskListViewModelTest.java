package com.gmail.nowak.wjw.simpletasks.presentation;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.gmail.nowak.wjw.simpletasks.data.model.TaskStatus;
import com.gmail.nowak.wjw.simpletasks.data.model.TaskViewData;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TaskListViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    TaskListViewModel viewModel;


    @Before
    public void setUp() throws Exception {
        viewModel = new TaskListViewModel();
    }

    @Test
    public void testInitialization() {
        List<TaskViewData> list = viewModel.getTasksLD().getValue();
        assertEquals(24, list.size());
    }
}