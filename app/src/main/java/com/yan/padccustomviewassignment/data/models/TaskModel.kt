package com.yan.padccustomviewassignment.data.models

import com.yan.padccustomviewassignment.data.vos.TaskVO
import io.reactivex.rxjava3.core.Observable

interface TaskModel {
    fun getTasks(): Observable<List<TaskVO>>
}