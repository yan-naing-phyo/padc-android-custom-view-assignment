package com.yan.padccustomviewassignment.data.models

import android.util.Log
import com.yan.padccustomviewassignment.data.vos.AssigneeVO
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

private const val TAG = "CreateTaskModelImpl"

object CreateTaskModelImpl : CreateTaskModel {
    private val mAssignees = mutableListOf<AssigneeVO>()
    private val mAssigneeListSubject = PublishSubject.create<List<AssigneeVO>>()

    override fun addAssignee(assigneeVO: AssigneeVO) {
        mAssignees.add(assigneeVO)
        mAssigneeListSubject.onNext(mAssignees)
        Log.d(TAG, "addAssignee: called: ${mAssignees}")
    }

    override fun getAssigneeList(): Observable<List<AssigneeVO>> =
        mAssigneeListSubject
}