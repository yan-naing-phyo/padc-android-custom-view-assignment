package com.yan.padccustomviewassignment.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yan.padccustomviewassignment.R
import com.yan.padccustomviewassignment.data.vos.TaskVO
import com.yan.padccustomviewassignment.ui.mvp.presenters.MainPresenter
import com.yan.padccustomviewassignment.ui.mvp.views.MainView

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpPresenter()
    }

    private fun setUpPresenter() {
        mPresenter =
    }

    override fun displayTasks(tasks: List<TaskVO>) {

    }
}