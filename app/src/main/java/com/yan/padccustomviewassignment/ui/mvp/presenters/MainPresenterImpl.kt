package com.yan.padccustomviewassignment.ui.mvp.presenters

import androidx.lifecycle.ViewModel
import com.yan.padccustomviewassignment.data.models.ProjectModel
import com.yan.padccustomviewassignment.data.models.ProjectModelImpl
import com.yan.padccustomviewassignment.data.models.TaskModel
import com.yan.padccustomviewassignment.data.models.TaskModelImpl
import com.yan.padccustomviewassignment.data.vos.AssigneeVO
import com.yan.padccustomviewassignment.ui.mvp.views.MainView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainPresenterImpl : MainPresenter, ViewModel() {
    private lateinit var mView: MainView

    private val mProjectModel: ProjectModel = ProjectModelImpl
    private val mTaskModel: TaskModel = TaskModelImpl

    override fun onUiReady() {
        mProjectModel.getProject().subscribeOn(Schedulers.io())
            .map {
                val mutableAssignees = it.assignees.toMutableList()
                mutableAssignees.add(AssigneeVO.NEW_TASK)
                it.copy(assignees = mutableAssignees)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                mView.displayProjectData(it)
            }

        mTaskModel.getTasks().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { tasks ->
                mView.displayTasks(tasks)
            }
    }

    override fun initView(view: MainView) {
        mView = view
    }

    override fun onTapProfile() {
        mView.showProfileDialog()
    }

    override fun onTapNewTask() {
        mView.navigateToCreateTaskScreen()
    }
}