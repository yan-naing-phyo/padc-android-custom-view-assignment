package com.yan.padccustomviewassignment.data.models

import com.yan.padccustomviewassignment.data.vos.AssigneeVO
import io.reactivex.rxjava3.core.Observable

interface CreateTaskModel {
    fun addAssignee(assigneeVO: AssigneeVO)
    fun getAssigneeList(): Observable<List<AssigneeVO>>
}