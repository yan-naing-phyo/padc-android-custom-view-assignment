package com.yan.padccustomviewassignment.ui.mvp.presenters

import androidx.lifecycle.ViewModel
import com.yan.padccustomviewassignment.data.models.TaskModel
import com.yan.padccustomviewassignment.data.models.TaskModelImpl
import com.yan.padccustomviewassignment.ui.mvp.views.TaskListView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class TaskListPresenterImpl : TaskListPresenter, ViewModel() {
    private val mTaskModel: TaskModel = TaskModelImpl
    private lateinit var mView: TaskListView

    override fun onUiReady() {
        mTaskModel.getTasks().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                mView.displayTaskList(it)
            }

    }

    override fun initView(view: TaskListView) {
        mView = view
    }

    override fun onTapProfile() {

    }

    override fun onTapNewTask() {

    }
}