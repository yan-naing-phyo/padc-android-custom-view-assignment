package com.yan.padccustomviewassignment.ui.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.yan.padccustomviewassignment.R
import com.yan.padccustomviewassignment.data.vos.AssigneeVO
import com.yan.padccustomviewassignment.ui.components.CircleImageView
import com.yan.padccustomviewassignment.ui.delegates.ProfileDelegate
import com.yan.padccustomviewassignment.ui.viewholders.AssigneeViewHolder

class AssigneeAdapter(
    private val mAvatarSize: Int,
    private val mProfileDelegate: ProfileDelegate
) :
    ListAdapter<AssigneeVO, AssigneeViewHolder>(AssigneeDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssigneeViewHolder {
        val context = parent.context

        // Create instance of [CircleImageView].
        val circleImageView = CircleImageView(context)

        // Layout params used in circle image view.
        val params = ViewGroup.LayoutParams(mAvatarSize, mAvatarSize)

        circleImageView.apply {
            id = View.generateViewId()
            layoutParams = params
            scaleType = ImageView.ScaleType.CENTER_CROP
            setStrokeWidth(context.resources.getDimension(R.dimen.margin_small))
        }

        val relativeLayout = RelativeLayout(context)
        // Add circle image view to viewGroup.
        relativeLayout.addView(circleImageView)

        return AssigneeViewHolder(relativeLayout, circleImageView.id, mProfileDelegate)
    }

    override fun onBindViewHolder(holder: AssigneeViewHolder, position: Int) {
        val assignee = getItem(position)
        holder.bind(assignee)
    }

    class AssigneeDiffUtil : DiffUtil.ItemCallback<AssigneeVO>() {
        override fun areItemsTheSame(oldItem: AssigneeVO, newItem: AssigneeVO): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: AssigneeVO, newItem: AssigneeVO): Boolean =
            oldItem == newItem
    }
}