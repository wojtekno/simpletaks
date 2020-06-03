package com.gmail.nowak.wjw.simpletasks.presentation;

import android.content.res.Resources;
import android.view.View;
import android.widget.Button;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.nowak.wjw.simpletasks.R;
import com.gmail.nowak.wjw.simpletasks.data.model.TaskStatus;
import com.gmail.nowak.wjw.simpletasks.data.model.TaskViewData;

import java.util.List;

public class ListActivityBindings {

    /**
     * Populate a recycler's adapter with the provided list
     * @param recyclerView
     * @param list
     */
    @BindingAdapter(value = "adapterData")
    public static void setAdapterData(RecyclerView recyclerView, List<TaskViewData> list) {
        TaskListAdapter adapter = (TaskListAdapter) recyclerView.getAdapter();
        if (adapter != null & list != null) {
            adapter.submitList(list);
        }
    }

    /**
     * Set provided button's text depending on the provided TaskStatus
     * @param button
     * @param taskStatus
     */
    @BindingAdapter(value = "buttonAction")
    public static void setButtonText(Button button, TaskStatus taskStatus) {
        String action;
        Resources res = button.getResources();
        String[] actionNames = res.getStringArray(R.array.button_action_names_array);
        action = actionNames[taskStatus.ordinal()];
        button.setText(action);
    }

    /**
     * Set view's background color depending on the provided TaskStatus
     * @param view
     * @param taskStatus
     */
    @BindingAdapter(value = "layoutColor")
    public static void setLayoutColor(View view, TaskStatus taskStatus) {
        Resources res = view.getResources();
        int[] colors = res.getIntArray(R.array.item_task_colors_array);
        int colorId;
        colorId = colors[taskStatus.ordinal()];
        view.setBackgroundColor(colorId);
    }

}
