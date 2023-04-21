package com.yan.padccustomviewassignment.ui.activities

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.yan.padccustomviewassignment.R
import com.yan.padccustomviewassignment.data.vos.AssigneeVO
import com.yan.padccustomviewassignment.databinding.ActivityCreateTaskBinding
import com.yan.padccustomviewassignment.ui.fragments.CreateTaskBottomSheetDialog
import com.yan.padccustomviewassignment.ui.mvp.presenters.CreateTaskPresenter
import com.yan.padccustomviewassignment.ui.mvp.presenters.CreateTaskPresenterImpl
import com.yan.padccustomviewassignment.ui.mvp.views.CreateTaskView
import com.yan.padccustomviewassignment.ui.viewpods.AssigneeListViewPod

class CreateTaskActivity : AppCompatActivity(), CreateTaskView {
    companion object {
        fun newIntent(context: Context): Intent = Intent(context, CreateTaskActivity::class.java)
    }

    // Presenter variable
    private lateinit var mPresenter: CreateTaskPresenter

    private lateinit var mBinding: ActivityCreateTaskBinding
    private lateinit var mAssigneeListViewPod: AssigneeListViewPod

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityCreateTaskBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setUpPresenter()
        setUpToolbar()
        setUpClientSpinner()
        setUpStartDateSpinner()
        setUpEndDateSpinner()
        setUpViewPods()
        mPresenter.onUiReady()
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[CreateTaskPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setUpViewPods() {
        mAssigneeListViewPod = mBinding.viewPodAssigneeList.root
        mAssigneeListViewPod.setUp(profileDelegate = mPresenter)
    }

    private fun setUpEndDateSpinner() {
        val adapter = ArrayAdapter(
            this, R.layout.item_spinner_duration,
            R.id.tvDuration,
            resources.getStringArray(R.array.end_date_array)
        )
        adapter.setDropDownViewResource(R.layout.item_spinner_dropdown)
        mBinding.spinnerEndDate.adapter = adapter
    }

    private fun setUpStartDateSpinner() {
        val adapter = ArrayAdapter(
            this,
            R.layout.item_spinner_duration,
            R.id.tvDuration,
            resources.getStringArray(R.array.start_date_array)
        )
        adapter.setDropDownViewResource(R.layout.item_spinner_dropdown)
        mBinding.spinnerStartDate.adapter = adapter
    }

    private fun setUpClientSpinner() {
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.client_array,
            R.layout.item_spinner
        )
        adapter.setDropDownViewResource(R.layout.item_spinner_dropdown)
        mBinding.spinnerClient.adapter = adapter
    }

    private fun setUpToolbar() {
        setSupportActionBar(mBinding.toolbar)
        supportActionBar?.apply {
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_ios_white_24dp)
            setDisplayHomeAsUpEnabled(true)
            title = "Create New Task"
        }
        mBinding.toolbar.navigationIcon?.setTint(Color.WHITE)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_create_task_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.actionSave -> {
                CreateTaskBottomSheetDialog.new()
                    .show(supportFragmentManager, CreateTaskBottomSheetDialog.TAG)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun displayAssigneeList(assignees: List<AssigneeVO>) {
        mAssigneeListViewPod.setData(assignees)
    }
}