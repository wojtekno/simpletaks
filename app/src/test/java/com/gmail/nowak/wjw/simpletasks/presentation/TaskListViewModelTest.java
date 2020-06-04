package com.gmail.nowak.wjw.simpletasks.presentation;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.gmail.nowak.wjw.simpletasks.data.model.TaskStatus;
import com.gmail.nowak.wjw.simpletasks.data.model.TaskViewData;
import com.gmail.nowak.wjw.simpletasks.domain.GetAllTasksUseCase;
import com.gmail.nowak.wjw.simpletasks.presentation.list.model.TaskListViewModel;

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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class TaskListViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private GetAllTasksUseCase allTasksUseCase;
    private TaskListViewModel viewModel;


    @Before
    public void setUp() throws Exception {
        MutableLiveData<List<TaskViewData>> liveData = new MutableLiveData<>(generateDummyData());
        when(allTasksUseCase.getAllTasks()).thenReturn(liveData);
        viewModel = new TaskListViewModel(allTasksUseCase);
    }

    @Test
    public void testViewModelInitialization() {
        List<TaskViewData> list = viewModel.getTasksLD().getValue();
        assertEquals(28, list.size());
    }

    @Test
    public void testAllTasksStatusesOpen() {
        List<TaskViewData> list = viewModel.getTasksLD().getValue();
        for (TaskViewData task : list) {
            assertEquals(TaskStatus.OPEN, task.getStatus());
        }
    }

    @Test
    public void testChangeStatusFromOpenToTravelling() {
        boolean statusChanged = viewModel.changeTaskStatus(0);
        assertTrue(statusChanged);
        List<TaskViewData> list = viewModel.getTasksLD().getValue();

        TaskViewData task = list.get(0);
        assertEquals(TaskStatus.TRAVELLING, task.getStatus());
        for (int i = 1; i < list.size(); i++) {
            task = list.get(i);
            assertEquals(TaskStatus.OPEN, task.getStatus());
        }
    }

    @Test
    public void testChangeStatusFromOpenThroughTravellingToWorking() {
        boolean statusChanged = viewModel.changeTaskStatus(0);
        assertTrue(statusChanged);
        statusChanged = viewModel.changeTaskStatus(0);
        assertTrue(statusChanged);
        List<TaskViewData> list = viewModel.getTasksLD().getValue();

        TaskViewData task = list.get(0);
        assertEquals(TaskStatus.WORKING, task.getStatus());
        for (int i = 1; i < list.size(); i++) {
            task = list.get(i);
            assertEquals(TaskStatus.OPEN, task.getStatus());
        }
    }

    @Test
    public void testChangeStatusFromOpenThroughTravellingAndWorkingToOpen() {
        boolean statusChanged = viewModel.changeTaskStatus(0);
        assertTrue(statusChanged);
        statusChanged = viewModel.changeTaskStatus(0);
        assertTrue(statusChanged);
        statusChanged = viewModel.changeTaskStatus(0);
        assertTrue(statusChanged);
        List<TaskViewData> list = viewModel.getTasksLD().getValue();

        TaskViewData task = list.get(0);
        assertEquals(TaskStatus.OPEN, task.getStatus());
        for (int i = 1; i < list.size(); i++) {
            task = list.get(i);
            assertEquals(TaskStatus.OPEN, task.getStatus());
        }
    }

    @Test
    public void testChangeStatusOfOneElementAndTryToChangeStatusOfAnotherOne() {
        boolean statusChanged = viewModel.changeTaskStatus(0);
        assertTrue(statusChanged);
        statusChanged = viewModel.changeTaskStatus(1);
        assertFalse(statusChanged);
        List<TaskViewData> list = viewModel.getTasksLD().getValue();

        TaskViewData task = list.get(0);
        assertEquals(TaskStatus.TRAVELLING, task.getStatus());
        task = list.get(1);
        assertEquals(TaskStatus.OPEN, task.getStatus());
        for (int i = 2; i < list.size(); i++) {
            task = list.get(i);
            assertEquals(TaskStatus.OPEN, task.getStatus());
        }
    }

    @Test
    public void testChangeStatusOfASecondTaskWhenPreviousOneChangedToOpen() {
        boolean statusChanged = viewModel.changeTaskStatus(0);
        assertTrue(statusChanged);
        statusChanged = viewModel.changeTaskStatus(0);
        assertTrue(statusChanged);
        statusChanged = viewModel.changeTaskStatus(0);
        assertTrue(statusChanged);
        statusChanged = viewModel.changeTaskStatus(1);
        assertTrue(statusChanged);
        List<TaskViewData> list = viewModel.getTasksLD().getValue();

        TaskViewData task = list.get(0);
        assertEquals(TaskStatus.OPEN, task.getStatus());
        task = list.get(1);
        assertEquals(TaskStatus.TRAVELLING, task.getStatus());
        for (int i = 2; i < list.size(); i++) {
            task = list.get(i);
            assertEquals(TaskStatus.OPEN, task.getStatus());
        }
    }

    private List<TaskViewData> generateDummyData() {
        List<TaskViewData> mList = new ArrayList<>();

        int id = 0;
        for (int c = 65; c < 69; c++) {
            for (int i = 1; i < 8; i++) {
                char ch = (char) c;
                String taskName = String.format("Task %s%d", ch, i);
                mList.add(new TaskViewData(id, taskName, TaskStatus.OPEN));
                id++;
            }
        }
        return mList;
    }


}