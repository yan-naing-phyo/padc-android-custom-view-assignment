package com.yan.padccustomviewassignment.ui.mvp.views

import com.yan.padccustomviewassignment.data.vos.AssigneeVO

interface CreateTaskView {
    fun displayAssigneeList(assignees: List<AssigneeVO>)
}