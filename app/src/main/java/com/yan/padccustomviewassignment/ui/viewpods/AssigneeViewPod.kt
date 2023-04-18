package com.yan.padccustomviewassignment.ui.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.yan.padccustomviewassignment.data.vos.AssigneeVO
import com.yan.padccustomviewassignment.databinding.ViewPodAssigneeBinding
import com.yan.padccustomviewassignment.ui.adapters.AssigneeAdapter
import com.yan.padccustomviewassignment.ui.utils.OverlapItemDecoration

class AssigneeViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {
    private lateinit var mBinding: ViewPodAssigneeBinding
    private val mAssigneeAdapter: AssigneeAdapter by lazy { AssigneeAdapter() }

    override fun onFinishInflate() {
        super.onFinishInflate()
        mBinding = ViewPodAssigneeBinding.bind(this)

        setUpAssigneeRecyclerView()
    }

    private fun setUpAssigneeRecyclerView() {
        val linearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        mBinding.rvAssignees.apply {
            adapter = mAssigneeAdapter
            addItemDecoration(OverlapItemDecoration())
            layoutManager = linearLayoutManager
        }
    }

    fun setData(data: List<AssigneeVO>) {
        mAssigneeAdapter.submitList(data)
    }
}