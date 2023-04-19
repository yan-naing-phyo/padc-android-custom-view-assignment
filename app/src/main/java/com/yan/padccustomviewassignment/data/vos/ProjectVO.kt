package com.yan.padccustomviewassignment.data.vos

data class ProjectVO(
    val client: String,
    val projectName: String,
    val assignees: List<AssigneeVO>
)
