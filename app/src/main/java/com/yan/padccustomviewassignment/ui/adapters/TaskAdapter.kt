package com.yan.padccustomviewassignment.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.yan.padccustomviewassignment.data.vos.TaskVO
import com.yan.padccustomviewassignment.databinding.ViewHolderTaskBinding
import com.yan.padccustomviewassignment.ui.delegates.ProfileDelegate
import com.yan.padccustomviewassignment.ui.viewholders.TaskViewHolder

class TaskAdapter(
    private val mProfileDelegate: ProfileDelegate
) : ListAdapter<TaskVO, TaskViewHolder>(TaskDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ViewHolderTaskBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TaskViewHolder(binding, mProfileDelegate = mProfileDelegate)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task)
    }
}

class TaskDiffUtil : DiffUtil.ItemCallback<TaskVO>() {
    override fun areItemsTheSame(oldTask: TaskVO, newTask: TaskVO): Boolean =
        oldTask.id == newTask.id

    override fun areContentsTheSame(oldTask: TaskVO, newTask: TaskVO): Boolean =
        oldTask == newTask
}
