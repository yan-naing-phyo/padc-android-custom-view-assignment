package com.yan.padccustomviewassignment.ui.mvp.presenters

import com.yan.padccustomviewassignment.ui.delegates.ProfileDelegate
import com.yan.padccustomviewassignment.ui.mvp.views.CreateTaskView

interface CreateTaskPresenter : ProfileDelegate {
    fun onUiReady()
    fun initView(view: CreateTaskView)
}