package com.yan.padccustomviewassignment.ui.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import com.yan.padccustomviewassignment.R
import com.yan.padccustomviewassignment.utils.dpToPx

class LinearProgress(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    companion object {
        private val DEFAULT_PROGRESS_TRACK_COLOR = Color.argb(100, 96, 189, 158)
        private val DEFAULT_PROGRESS_ACTIVE_COLOR = Color.argb(255, 96, 189, 158)
        private const val DEFAULT_PROGRESS_WIDTH_IN_DP = 8f
    }

    // Color variables
    private var mProgressTrackColor = 0
    private var mProgressActiveColor = 0

    // Paint variables
    private val mProgressTrackPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mProgressActivePaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var mProgressWidth = 0f
    private var mProgress = 0

    init {
        context?.withStyledAttributes(attrs, R.styleable.LinearProgress) {
            mProgressTrackColor = getColor(
                R.styleable.LinearProgress_progressTrackColor,
                DEFAULT_PROGRESS_TRACK_COLOR
            )
            mProgressActiveColor = getColor(
                R.styleable.LinearProgress_progressActiveColor,
                DEFAULT_PROGRESS_ACTIVE_COLOR
            )
            mProgressWidth = getDimension(
                R.styleable.LinearProgress_trackWidth,
                context.dpToPx(DEFAULT_PROGRESS_WIDTH_IN_DP)
            )
            mProgress = getInteger(R.styleable.LinearProgress_progress, 0)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(widthMeasureSpec, mProgressWidth.toInt())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawProgressTrack(canvas)
        drawActiveProgress(canvas)
    }

    private fun drawActiveProgress(canvas: Canvas) {
        mProgressActivePaint.apply {
            color = mProgressActiveColor
            style = Paint.Style.STROKE
            strokeWidth = mProgressWidth
        }

        canvas.drawLine(0f, 0f, width * mProgress / 100f, 0f, mProgressActivePaint)
    }

    private fun drawProgressTrack(canvas: Canvas) {
        mProgressTrackPaint.apply {
            color = mProgressTrackColor
            style = Paint.Style.STROKE
            strokeWidth = mProgressWidth
        }
        canvas.drawLine(0f, 0f, width.toFloat(), 0f, mProgressTrackPaint)
    }
}