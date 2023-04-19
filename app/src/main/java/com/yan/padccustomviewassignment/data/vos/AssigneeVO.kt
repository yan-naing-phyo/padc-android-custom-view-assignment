package com.yan.padccustomviewassignment.data.vos

data class AssigneeVO(
    val id: Int,
    val name: String,
    val profile: String
) {
    companion object {
        val MOCK = AssigneeVO(id = 1, name = "Tom", profile = "")
        val NEW_TASK = AssigneeVO(id = -1, name = "", profile = "")
    }
}
