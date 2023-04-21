package com.yan.padccustomviewassignment.ui.mvp.views

import com.yan.padccustomviewassignment.data.vos.TaskVO

interface TaskListView {
    fun displayTaskList(tasks: List<TaskVO>)
}