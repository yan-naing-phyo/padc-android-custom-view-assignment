package com.yan.padccustomviewassignment.data.models

import com.yan.padccustomviewassignment.data.vos.AssigneeVO
import com.yan.padccustomviewassignment.data.vos.ProjectVO
import io.reactivex.rxjava3.core.Observable

private val assignees = listOf(
    AssigneeVO(1, "Tom", ""),
    AssigneeVO(2, "Mary", ""),
    AssigneeVO(3, "Jimmy", ""),
)

private val projectVO = ProjectVO(
    client = "Awesome Team",
    projectName = "Awesome Project",
    assignees = assignees
)

object ProjectModelImpl : ProjectModel {
    override fun getProject(): Observable<ProjectVO> {
        return Observable.just(projectVO)
    }
}