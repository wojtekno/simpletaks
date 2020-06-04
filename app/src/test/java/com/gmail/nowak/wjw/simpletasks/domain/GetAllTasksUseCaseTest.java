package com.gmail.nowak.wjw.simpletasks.domain;

import com.gmail.nowak.wjw.simpletasks.data.model.TaskViewData;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GetAllTasksUseCaseTest {

    GetAllTasksUseCase useCase;

    @Before
    public void setUp() throws Exception {
        useCase = new GetAllTasksUseCase();
    }

    @Test
    public void getAllTasks(){
        List<TaskViewData> list = useCase.getAllTasks().getValue();
        assertEquals(28, list.size());
    }
}