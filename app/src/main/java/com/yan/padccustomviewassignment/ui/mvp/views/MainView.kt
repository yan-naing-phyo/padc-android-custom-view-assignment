package com.yan.padccustomviewassignment.ui.mvp.views

import com.yan.padccustomviewassignment.data.vos.ProjectVO
import com.yan.padccustomviewassignment.data.vos.TaskVO

interface MainView {
    fun displayProjectData(projectVO: ProjectVO)
    fun displayTasks(tasks: List<TaskVO>)
    fun showProfileDialog()
    fun navigateToCreateTaskScreen()
}