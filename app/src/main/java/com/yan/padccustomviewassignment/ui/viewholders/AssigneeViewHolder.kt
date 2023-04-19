package com.yan.padccustomviewassignment.ui.viewholders

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import com.yan.padccustomviewassignment.R
import com.yan.padccustomviewassignment.data.vos.AssigneeVO
import com.yan.padccustomviewassignment.ui.components.CircleImageView
import com.yan.padccustomviewassignment.ui.delegates.ProfileDelegate

class AssigneeViewHolder(
    itemView: View,
    viewId: Int,
    private val mProfileDelegate: ProfileDelegate
) :
    RecyclerView.ViewHolder(itemView) {

    private val mImageView = itemView.findViewById<CircleImageView>(viewId)

    fun bind(assigneeVO: AssigneeVO) {
        if (assigneeVO == AssigneeVO.NEW_TASK) {
            mImageView.apply {
                // Set add icon as image src.
                setImageDrawable(
                    AppCompatResources.getDrawable(
                        context,
                        R.drawable.ic_add_white_24dp
                    )
                )
                backgroundTintList = ColorStateList.valueOf(Color.WHITE)
                setPadding(48)
                imageTintList = ColorStateList.valueOf(Color.BLACK)
            }
        } else {
            itemView.setOnClickListener {
                mProfileDelegate.onTapProfile()
            }
            mImageView.apply {
                setImageDrawable(
                    AppCompatResources.getDrawable(
                        context,
                        R.drawable.placeholder_bcs
                    )
                )
            }
        }
    }
}