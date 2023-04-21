package com.yan.padccustomviewassignment.ui.mvp.presenters

import com.yan.padccustomviewassignment.ui.delegates.ProfileDelegate
import com.yan.padccustomviewassignment.ui.mvp.views.MainView

interface MainPresenter : ProfileDelegate {
    fun onUiReady()
    fun initView(view: MainView)
}