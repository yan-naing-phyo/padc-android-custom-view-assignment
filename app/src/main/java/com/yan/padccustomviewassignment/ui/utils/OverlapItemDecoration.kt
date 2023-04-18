package com.yan.padccustomviewassignment.ui.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class OverlapItemDecoration : RecyclerView.ItemDecoration() {
    companion object {
        private const val HORIZONTAL_OVERLAP = -60
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemPosition = parent.getChildAdapterPosition(view)
        if (itemPosition == 0) return
        outRect.set(HORIZONTAL_OVERLAP, 0, 0, 0)
    }
}