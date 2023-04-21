package com.yan.padccustomviewassignment.ui.mvp.presenters

import com.yan.padccustomviewassignment.ui.delegates.ProfileDelegate
import com.yan.padccustomviewassignment.ui.mvp.views.TaskListView

interface TaskListPresenter : ProfileDelegate {
    fun onUiReady()
    fun initView(view: TaskListView)
}