package com.yan.padccustomviewassignment.data.models

import com.yan.padccustomviewassignment.data.vos.TaskStatus
import com.yan.padccustomviewassignment.data.vos.TaskVO
import io.reactivex.rxjava3.core.Observable

private val tasks = listOf(
    TaskVO(id = 1, taskName = "Contact page"),
    TaskVO(id = 2, taskName = "Calculator page", taskStatus = TaskStatus.DONE),
    TaskVO(id = 3, taskName = "Technical task", taskStatus = TaskStatus.REVIEW)
)

object TaskModelImpl : TaskModel {
    override fun getTasks(): Observable<List<TaskVO>> {
        return Observable.just(tasks)
    }
}