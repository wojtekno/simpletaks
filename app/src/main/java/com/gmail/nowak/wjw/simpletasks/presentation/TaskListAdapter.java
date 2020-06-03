package com.gmail.nowak.wjw.simpletasks.presentation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.nowak.wjw.simpletasks.R;
import com.gmail.nowak.wjw.simpletasks.data.model.TaskViewData;
import com.gmail.nowak.wjw.simpletasks.databinding.ItemTaskBinding;

public class TaskListAdapter extends ListAdapter<TaskViewData, TaskListAdapter.TaskViewHolder> {


    protected TaskListAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTaskBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_task, parent, false);
        return new TaskViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.binding.setTask(getItem(position));
    }


    class TaskViewHolder extends RecyclerView.ViewHolder {
        ItemTaskBinding binding;

        public TaskViewHolder(@NonNull ItemTaskBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public static final DiffUtil.ItemCallback<TaskViewData> DIFF_CALLBACK = new DiffUtil.ItemCallback<TaskViewData>() {
        @Override
        public boolean areItemsTheSame(@NonNull TaskViewData oldItem, @NonNull TaskViewData newItem) {
            return oldItem==newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull TaskViewData oldItem, @NonNull TaskViewData newItem) {
            return oldItem.equals(newItem);
        }
    };
}
