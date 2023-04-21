package com.yan.padccustomviewassignment.ui.mvp.presenters

import android.util.Log
import androidx.lifecycle.ViewModel
import com.yan.padccustomviewassignment.data.models.CreateTaskModel
import com.yan.padccustomviewassignment.data.models.CreateTaskModelImpl
import com.yan.padccustomviewassignment.data.vos.AssigneeVO
import com.yan.padccustomviewassignment.ui.mvp.views.CreateTaskView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

private const val TAG = "CreateTaskPresenterImpl"

class CreateTaskPresenterImpl : CreateTaskPresenter, ViewModel() {
    private val mCreateTaskModel: CreateTaskModel = CreateTaskModelImpl
    private lateinit var mView: CreateTaskView

    override fun onTapProfile() {

    }

    override fun onTapNewTask() {
        Log.d(TAG, "onTapNewTask: called")
        mCreateTaskModel.addAssignee(AssigneeVO.MOCK)
    }

    override fun onUiReady() {
        mCreateTaskModel.getAssigneeList()
            .observeOn(Schedulers.io())
            .map {
                val newList = it.toMutableList()
                newList.add(AssigneeVO.NEW_TASK)
                newList
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { assignees ->
                mView.displayAssigneeList(assignees)
                Log.d(TAG, "subscribe: called: $assignees")
            }
        mCreateTaskModel.addAssignee(AssigneeVO.MOCK)
    }

    override fun initView(view: CreateTaskView) {
        mView = view
    }
}