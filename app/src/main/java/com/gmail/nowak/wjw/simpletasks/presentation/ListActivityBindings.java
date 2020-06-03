package com.gmail.nowak.wjw.simpletasks.presentation;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.nowak.wjw.simpletasks.data.model.TaskViewData;

import java.util.List;

import timber.log.Timber;

public class ListActivityBindings {

    @BindingAdapter(value = "adapterData")
    public static void setAdapterData(RecyclerView recyclerView, List<TaskViewData> list) {
        TaskListAdapter adapter = (TaskListAdapter) recyclerView.getAdapter();
        if (adapter != null & list != null) {
            adapter.submitList(list);
        }
    }
}
