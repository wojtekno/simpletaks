package com.gmail.nowak.wjw.simpletasks.presentation.list.activity;

import android.content.res.Resources;
import android.view.View;
import android.widget.Button;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.nowak.wjw.simpletasks.R;
import com.gmail.nowak.wjw.simpletasks.data.model.TaskStatus;
import com.gmail.nowak.wjw.simpletasks.data.model.TaskViewData;

import java.util.List;

import timber.log.Timber;

public class ListActivityBindings {

    /**
     * Populate a recycler's adapter with the provided list and set appropriate flag
     *
     * @param recyclerView
     * @param list                list of TaskViewData to display in ListAdapter
     * @param isAnyTaskInProgress flag informing if there is already a task in progress
     */
    @BindingAdapter(value = {"adapterData", "isAnyTaskInProgress"})
    public static void setAdapterData(RecyclerView recyclerView, List<TaskViewData> list, boolean isAnyTaskInProgress) {
        TaskListAdapter adapter = (TaskListAdapter) recyclerView.getAdapter();
        if (adapter != null & list != null) {
            adapter.setTaskInProgress(isAnyTaskInProgress);
            adapter.submitList(list);
        }
    }


    /**
     * Set view's background color depending on the provided TaskStatus
     *
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

    /**
     * Set provided button's text and visibility depending on the provided TaskStatus and flag
     *
     * @param button
     * @param taskStatus
     * @param isAnyInProgress flag informing if any other task already open
     */
    @BindingAdapter(value = {"buttonAction", "android:visibility"})
    public static void setButtonTextAndVisibility(Button button, TaskStatus taskStatus, Boolean isAnyInProgress) {
        if (TaskStatus.OPEN == taskStatus && isAnyInProgress) {
            button.setVisibility(View.INVISIBLE);
        } else {
            String action;
            Resources res = button.getResources();
            String[] actionNames = res.getStringArray(R.array.button_action_names_array);
            action = actionNames[taskStatus.ordinal()];
            button.setText(action);
            button.setVisibility(View.VISIBLE);
        }
    }

}
