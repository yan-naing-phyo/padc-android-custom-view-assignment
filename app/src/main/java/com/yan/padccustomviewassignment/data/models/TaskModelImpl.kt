package com.yan.padccustomviewassignment.data.models

import com.yan.padccustomviewassignment.data.vos.AssigneeVO
import com.yan.padccustomviewassignment.data.vos.TaskStatus
import com.yan.padccustomviewassignment.data.vos.TaskVO
import io.reactivex.rxjava3.core.Observable

private val tasks = listOf(
    TaskVO(
        id = 1,
        taskName = "Contact page",
        tag = "#Design",
        assigneeList = listOf(AssigneeVO.MOCK, AssigneeVO.MOCK)
    ),
    TaskVO(
        id = 2,
        taskName = "Calculator page",
        taskStatus = TaskStatus.DONE,
        tag = "#Design",
        assigneeList = listOf(AssigneeVO.MOCK)
    ),
    TaskVO(
        id = 3,
        taskName = "Technical task",
        taskStatus = TaskStatus.REVIEW,
        tag = "#Frontend",
        assigneeList = listOf(AssigneeVO.MOCK, AssigneeVO.MOCK)
    )
)

object TaskModelImpl : TaskModel {
    override fun getTasks(): Observable<List<TaskVO>> {
        return Observable.just(tasks)
    }
}