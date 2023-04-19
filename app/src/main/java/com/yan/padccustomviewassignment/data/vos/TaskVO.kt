package com.yan.padccustomviewassignment.data.vos

import android.graphics.Color

private val COLOR_IN_PROGRESS = Color.argb(255, 231, 111, 81)
private val COLOR_DONE = Color.argb(255, 2, 62, 138)
private val COLOR_REVIEW = Color.argb(255, 109, 104, 117)

enum class TaskStatus(val color: Int, val status: String) {
    IN_PROGRESS(
        color = COLOR_IN_PROGRESS,
        status = "In progress"
    ),
    DONE(color = COLOR_DONE, status = "Done"),
    REVIEW(color = COLOR_REVIEW, status = "Review")
}

data class TaskVO(
    val assigneeList: List<AssigneeVO>,
    val attachedFiles: List<AttachedFileVO> = emptyList(),
    val comments: List<CommentVO> = emptyList(),
    val id: Int,
    val tag: String,
    val taskName: String,
    val taskStatus: TaskStatus = TaskStatus.IN_PROGRESS
)
