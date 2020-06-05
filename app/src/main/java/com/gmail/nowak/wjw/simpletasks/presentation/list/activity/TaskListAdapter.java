package com.gmail.nowak.wjw.simpletasks.presentation.list.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.nowak.wjw.simpletasks.R;
import com.gmail.nowak.wjw.simpletasks.data.model.TaskViewData;
import com.gmail.nowak.wjw.simpletasks.databinding.ItemTaskBinding;

import timber.log.Timber;

public class TaskListAdapter extends ListAdapter<TaskViewData, TaskListAdapter.TaskViewHolder> {

    private ChangeStatusOnClickListener listener;
    private MutableLiveData<Boolean> isAnyTaskInProgress = new MutableLiveData<>(false);
    private LifecycleOwner lifecycleOwner;

    protected TaskListAdapter(ChangeStatusOnClickListener changeStatusOnClickListener, LifecycleOwner lifecycleOwner) {
        super(DIFF_CALLBACK);
        Timber.d("TaskListAdapter::newInstance");
        listener = changeStatusOnClickListener;
        this.lifecycleOwner = lifecycleOwner;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTaskBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_task, parent, false);

        return new TaskViewHolder(itemBinding, isAnyTaskInProgress);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, final int position) {
        holder.binding.setTask(getItem(position));
        holder.binding.setLifecycleOwner(lifecycleOwner);
        holder.binding.executePendingBindings();
    }

    public void setTaskInProgress(boolean isTaskInProgress) {
        this.isAnyTaskInProgress.setValue(isTaskInProgress);
    }


    class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemTaskBinding binding;

        public TaskViewHolder(@NonNull ItemTaskBinding binding, LiveData<Boolean> progressFlag) {
            super(binding.getRoot());
            this.binding = binding;
            binding.setIsAnyInProgress(progressFlag);
            binding.changeStatusButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.changeStatusClicked(getAdapterPosition());
        }
    }

    public static final DiffUtil.ItemCallback<TaskViewData> DIFF_CALLBACK = new DiffUtil.ItemCallback<TaskViewData>() {
        @Override
        public boolean areItemsTheSame(@NonNull TaskViewData oldItem, @NonNull TaskViewData newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull TaskViewData oldItem, @NonNull TaskViewData newItem) {
            return oldItem.equals(newItem);
        }
    };

    interface ChangeStatusOnClickListener {
        void changeStatusClicked(int listPosition);
    }
}
