package com.yan.padccustomviewassignment.data.models

import com.yan.padccustomviewassignment.data.vos.ProjectVO
import io.reactivex.rxjava3.core.Observable

interface ProjectModel {
    fun getProject(): Observable<ProjectVO>
}