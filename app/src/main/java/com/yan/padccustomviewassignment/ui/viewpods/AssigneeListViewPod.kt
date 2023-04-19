package com.yan.padccustomviewassignment.ui.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.yan.padccustomviewassignment.data.vos.AssigneeVO
import com.yan.padccustomviewassignment.databinding.ViewPodAssigneeListBinding
import com.yan.padccustomviewassignment.ui.adapters.AssigneeAdapter
import com.yan.padccustomviewassignment.ui.delegates.ProfileDelegate
import com.yan.padccustomviewassignment.ui.utils.OverlapItemDecoration
import com.yan.padccustomviewassignment.utils.dpToPx

class AssigneeListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {
    companion object {
        private const val DEFAULT_AVATAR_SIZE_IN_DP = 60f
    }

    private lateinit var mBinding: ViewPodAssigneeListBinding
    private lateinit var mProfileDelegate: ProfileDelegate
    private var mAvatarSize = 0f
    private val mAssigneeAdapter: AssigneeAdapter by lazy {
        AssigneeAdapter(
            mAvatarSize.toInt(),
            mProfileDelegate
        )
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        mBinding = ViewPodAssigneeListBinding.bind(this)
    }

    fun setUp(
        avatarSize: Float = context.dpToPx(DEFAULT_AVATAR_SIZE_IN_DP),
        profileDelegate: ProfileDelegate
    ) {
        mAvatarSize = avatarSize
        mProfileDelegate = profileDelegate
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