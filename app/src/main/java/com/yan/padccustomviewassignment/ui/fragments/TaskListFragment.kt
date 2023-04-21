package com.yan.padccustomviewassignment.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yan.padccustomviewassignment.R
import com.yan.padccustomviewassignment.data.vos.TaskVO
import com.yan.padccustomviewassignment.databinding.FragmentTaskListBinding
import com.yan.padccustomviewassignment.ui.adapters.TaskAdapter
import com.yan.padccustomviewassignment.ui.mvp.presenters.TaskListPresenter
import com.yan.padccustomviewassignment.ui.mvp.presenters.TaskListPresenterImpl
import com.yan.padccustomviewassignment.ui.mvp.views.TaskListView

class TaskListFragment : Fragment(R.layout.fragment_task_list), TaskListView {
    // Presenter variable
    private lateinit var mPresenter: TaskListPresenter

    private var _binding: FragmentTaskListBinding? = null
    private val mBinding: FragmentTaskListBinding
        get() = checkNotNull(_binding) {
            "Cannot get binding because it is null!"
        }

    private val mTaskAdapter: TaskAdapter by lazy { TaskAdapter(mPresenter) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTaskListBinding.bind(view)

        setUpPresenter()
        setUpTaskListRecyclerView()

    }

    private fun setUpTaskListRecyclerView() {
        val linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        mBinding.rvTaskList.apply {
            adapter = mTaskAdapter
            layoutManager = linearLayoutManager
        }
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[TaskListPresenterImpl::class.java]
        mPresenter.initView(this)
        mPresenter.onUiReady()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun displayTaskList(tasks: List<TaskVO>) {
        mTaskAdapter.submitList(tasks)
    }
}