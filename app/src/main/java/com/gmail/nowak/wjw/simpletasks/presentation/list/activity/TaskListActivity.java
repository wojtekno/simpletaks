package com.gmail.nowak.wjw.simpletasks.presentation.list.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.gmail.nowak.wjw.simpletasks.MyApplication;
import com.gmail.nowak.wjw.simpletasks.R;
import com.gmail.nowak.wjw.simpletasks.data.model.TaskStatus;
import com.gmail.nowak.wjw.simpletasks.data.model.TaskViewData;
import com.gmail.nowak.wjw.simpletasks.databinding.ActivityTaskListBinding;
import com.gmail.nowak.wjw.simpletasks.presentation.list.model.ListViewModelFactory_Factory;
import com.gmail.nowak.wjw.simpletasks.presentation.list.model.TaskListViewModel;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class TaskListActivity extends AppCompatActivity implements TaskListAdapter.ChangeStatusOnClickListener {

    private ActivityTaskListBinding binding;
    private TaskListViewModel viewModel;
    private Toast mToast;
    private final static String SAVED_POSITION_KEY = "position";
    private final static String SAVED_STATE_KEY = "state";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("onCreate");

        binding = DataBindingUtil.setContentView(this, R.layout.activity_task_list);
        binding.setLifecycleOwner(this);

        //restoring persisted data
        int savedPosition = getPreferences(MODE_PRIVATE).getInt(SAVED_POSITION_KEY, -1);
        String savedStatus = getPreferences(MODE_PRIVATE).getString(SAVED_STATE_KEY, TaskStatus.OPEN.name());

        ListViewModelFactory_Factory listViewModelFactory_factory = ((MyApplication) getApplication()).appGraph.listViewModelFactory_factory;
        viewModel = new ViewModelProvider(this, listViewModelFactory_factory.create(savedPosition, savedStatus)).get(TaskListViewModel.class);
        binding.setViewModel(viewModel);

        binding.setAdapter(new TaskListAdapter(this, this));
    }

    //todo delete
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

    /**
     * Informs viewModel about changeStatus button clicked
     * @param listPosition position of a TaskItem clicked
     */
    @Override
    public void changeStatusClicked(int listPosition) {
        //todo delete toastMessage feature
        if (mToast != null) {
            mToast.cancel();
        }
        if (viewModel.changeTaskStatus(listPosition)) {
            TaskViewData changedTask = viewModel.getTasksLD().getValue().get(listPosition);
            SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
            if (changedTask.getStatus() != TaskStatus.OPEN) {
                editor.putInt(SAVED_POSITION_KEY, listPosition);
                editor.putString(SAVED_STATE_KEY, changedTask.getStatus().name());
            } else {
                editor.remove(SAVED_POSITION_KEY);
                editor.remove(SAVED_STATE_KEY);
            }
            editor.commit();
            mToast = Toast.makeText(this, String.format("Posiotion %d clicked", listPosition), Toast.LENGTH_SHORT);
        } else {
            mToast = Toast.makeText(this, String.format(getString(R.string.cannot_work_two_simultaneously), listPosition), Toast.LENGTH_SHORT);
        }
        ;
        mToast.show();
    }

}