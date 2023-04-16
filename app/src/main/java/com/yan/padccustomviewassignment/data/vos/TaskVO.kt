package com.yan.padccustomviewassignment.data.vos

enum class TaskStatus {
    IN_PROGRESS, DONE, REVIEW
}

data class TaskVO(
    val attachedFiles: List<AttachedFileVO> = emptyList(),
    val comments: List<CommentVO> = emptyList(),
    val id: Int,
    val taskName: String,
    val taskStatus: TaskStatus = TaskStatus.IN_PROGRESS
)
