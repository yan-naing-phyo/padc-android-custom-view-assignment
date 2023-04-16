package com.yan.padccustomviewassignment.ui.mvp.views

import com.yan.padccustomviewassignment.data.vos.TaskVO

interface MainView {
    fun displayTasks(tasks: List<TaskVO>)
}