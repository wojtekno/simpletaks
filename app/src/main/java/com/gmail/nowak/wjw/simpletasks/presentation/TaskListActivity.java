package com.gmail.nowak.wjw.simpletasks.presentation;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.gmail.nowak.wjw.simpletasks.R;
import com.gmail.nowak.wjw.simpletasks.data.model.TaskStatus;
import com.gmail.nowak.wjw.simpletasks.data.model.TaskViewData;
import com.gmail.nowak.wjw.simpletasks.databinding.ActivityTaskListBinding;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class TaskListActivity extends AppCompatActivity implements TaskListAdapter.ChangeStatusOnClickListener {

    ActivityTaskListBinding binding;
    TaskListViewModel viewModel;
    Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_task_list);
        binding.setLifecycleOwner(this);

        viewModel = new ViewModelProvider(this).get(TaskListViewModel.class);
        binding.setViewModel(viewModel);

        TaskListAdapter adapter = new TaskListAdapter(this);
        binding.setAdapter(adapter);
    }


    private List<TaskViewData> dummyFromResources() {
        String[] strings = getResources().getStringArray(R.array.source_dummy_data);
        Timber.d("String array: %d", strings.length);
        List<TaskViewData> mList = new ArrayList<>();

        int id = 0;
        for (String string : strings) {
            mList.add(new TaskViewData(id, string, TaskStatus.OPEN));
            id++;
        }
        return mList;
    }


    @Override
    public void changeStatusClicked(int listPosition) {
        if(mToast!=null){
            mToast.cancel();
        }

        if(viewModel.changeTaskStatus(listPosition)){
            mToast =Toast.makeText(this, String.format("Posiotion %d clicked", listPosition), Toast.LENGTH_SHORT);
        } else {
            mToast= Toast.makeText(this, String.format(getString(R.string.cannot_work_two_simultaneously), listPosition), Toast.LENGTH_SHORT);
        };
        mToast.show();
    }
}