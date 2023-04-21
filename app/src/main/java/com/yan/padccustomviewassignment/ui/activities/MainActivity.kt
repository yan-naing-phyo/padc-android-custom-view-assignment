package com.yan.padccustomviewassignment.ui.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.ColorFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yan.padccustomviewassignment.R
import com.yan.padccustomviewassignment.data.vos.ProjectVO
import com.yan.padccustomviewassignment.data.vos.TaskVO
import com.yan.padccustomviewassignment.databinding.ActivityMainBinding
import com.yan.padccustomviewassignment.ui.adapters.TaskAdapter
import com.yan.padccustomviewassignment.ui.fragments.ProfileDialog
import com.yan.padccustomviewassignment.ui.mvp.presenters.MainPresenter
import com.yan.padccustomviewassignment.ui.mvp.presenters.MainPresenterImpl
import com.yan.padccustomviewassignment.ui.mvp.views.MainView
import com.yan.padccustomviewassignment.ui.viewpods.AssigneeListViewPod

class MainActivity : AppCompatActivity(), MainView {
    companion object {
        fun newIntent(context: Context): Intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
    }

    private lateinit var mPresenter: MainPresenter
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAssigneeListViewPod: AssigneeListViewPod
    private val mTaskAdapter: TaskAdapter by lazy { TaskAdapter(mPresenter) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setUpPresenter()
        setUpToolbar()
        setUpTaskListRecyclerView()
        setUpViewPods()
        setUpListeners()
        mPresenter.onUiReady()
    }

    private fun setUpToolbar() {
        setSupportActionBar(mBinding.toolbar)
        supportActionBar?.apply {
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_ios_white_24dp)
            setDisplayHomeAsUpEnabled(true)
            title = "OMP Dashboard"
        }
        mBinding.toolbar.navigationIcon?.setTint(Color.BLACK)
    }

    private fun setUpListeners() {

    }

    private fun setUpTaskListRecyclerView() {
        val linearLayoutManager =
            LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        mBinding.rvTaskList.apply {
            layoutManager = linearLayoutManager
            adapter = mTaskAdapter
        }
    }

    private fun setUpViewPods() {
        mAssigneeListViewPod = mBinding.viewPodAssigneeList.root
        mAssigneeListViewPod.setUp(profileDelegate = mPresenter)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[MainPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    override fun displayProjectData(projectVO: ProjectVO) {
        mAssigneeListViewPod.setData(projectVO.assignees)
    }

    override fun displayTasks(tasks: List<TaskVO>) {
        mTaskAdapter.submitList(tasks)
    }

    override fun showProfileDialog() {
        ProfileDialog.get().show(supportFragmentManager, ProfileDialog.TAG)
    }

    override fun navigateToCreateTaskScreen() {
        startActivity(CreateTaskActivity.newIntent(this))
    }
}