package com.yan.padccustomviewassignment.ui.viewholders

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.yan.padccustomviewassignment.R
import com.yan.padccustomviewassignment.data.vos.TaskVO
import com.yan.padccustomviewassignment.databinding.ViewHolderTaskBinding

class TaskViewHolder(private val binding: ViewHolderTaskBinding) : ViewHolder(binding.root) {
    private val mAssigneeListViewPod = binding.viewPodAssigneeList.root

    init {
        val context = binding.root.context
        mAssigneeListViewPod.setUp(context.resources.getDimension(R.dimen.margin_xxlarge))
    }

    fun bind(taskVO: TaskVO) {
        binding.apply {
            tvTaskStatus.text = taskVO.taskStatus.status
            tvTaskStatus.setTextColor(taskVO.taskStatus.color)
            tvTag.text = taskVO.tag
            tvTaskName.text = taskVO.taskName
        }

        mAssigneeListViewPod.setData(taskVO.assigneeList)
    }
}