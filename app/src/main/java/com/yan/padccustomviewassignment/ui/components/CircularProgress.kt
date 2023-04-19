package com.yan.padccustomviewassignment.ui.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import com.yan.padccustomviewassignment.R
import com.yan.padccustomviewassignment.utils.dpToPx
import com.yan.padccustomviewassignment.utils.spToPx

class CircularProgress(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    companion object {
        // DEFAULT COLOR VALUES
        private val DEFAULT_ACTIVE_INDICATOR_COLOR = Color.argb(255, 231, 111, 81)
        private val DEFAULT_PROGRESS_TRACK_COLOR = Color.argb(100, 231, 111, 81)
        private val DEFAULT_PROGRESS_LABEL_COLOR = Color.argb(255, 231, 111, 81)

        private const val DEFAULT_PROGRESS = 0
        private const val DEFAULT_TEXT_SIZE_IN_SP = 14f
        private const val DEFAULT_STROKE_WIDTH_IN_DP = 1f
        private const val PROGRESS_START_ANGLE = 270.0f
    }

    // Color variables used for active indicator color, progress track color and progress label color.
    private var mActiveIndicatorColor = 0
    private var mProgressTrackColor = 0
    private var mProgressLabelColor = 0

    private var mProgress = 0
    private var mStrokeWidth = 0f

    // Circle coordinates
    private var mCenterX = 0f
    private var mCenterY = 0f
    private var mRadius = 0f

    // Dimension for progress label text.
    private var mTextHeight = 0f
    private var mTextSize = 0f

    // Paint variables.
    private val mActiveIndicatorPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mTrackPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        context?.withStyledAttributes(attrs, R.styleable.CircularProgress) {
            mActiveIndicatorColor =
                getColor(
                    R.styleable.CircularProgress_activeIndicatorColor,
                    DEFAULT_ACTIVE_INDICATOR_COLOR
                )
            mProgressTrackColor =
                getColor(
                    R.styleable.CircularProgress_progressTrackColor,
                    DEFAULT_PROGRESS_TRACK_COLOR
                )
            mProgressLabelColor =
                getColor(
                    R.styleable.CircularProgress_progressLabelColor,
                    DEFAULT_PROGRESS_LABEL_COLOR
                )
            mProgress = getInteger(R.styleable.CircularProgress_progress, DEFAULT_PROGRESS)
            mTextSize = getDimension(
                R.styleable.CircularProgress_textSize, context.spToPx(DEFAULT_TEXT_SIZE_IN_SP)
            )
            mStrokeWidth = getDimension(
                R.styleable.CircularProgress_progressWidth,
                context.dpToPx(DEFAULT_STROKE_WIDTH_IN_DP)
            )
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        mRadius = (width.coerceAtMost(height) - mStrokeWidth) / 2f
        mCenterX = width / 2f
        mCenterY = height / 2f

        drawProgressTrack(canvas)
        drawProgressLabel(canvas)
        drawProgress(canvas)
    }

    private fun drawProgressTrack(canvas: Canvas) {
        // Track paint configuration.
        mTrackPaint.color = mProgressTrackColor
        mTrackPaint.style = Paint.Style.STROKE
        mTrackPaint.strokeWidth = mStrokeWidth

        canvas.drawCircle(mCenterX, mCenterY, mRadius, mTrackPaint)
    }

    private fun drawProgressLabel(canvas: Canvas) {
        // Text paint configuration.
        mTextPaint.color = mProgressLabelColor
        mTextPaint.textSize = mTextSize
        mTextPaint.textAlign = Paint.Align.CENTER

        val fontMetrics = mTextPaint.fontMetrics
        mTextHeight = fontMetrics.descent - fontMetrics.ascent

        val textOriginX = mCenterX
        val textBaselineY = mCenterY + (mTextHeight / 4f)
        canvas.drawText("$mProgress%", textOriginX, textBaselineY, mTextPaint)
    }

    private fun drawProgress(canvas: Canvas) {
        // Active indicator paint configuration
        mActiveIndicatorPaint.color = mActiveIndicatorColor
        mActiveIndicatorPaint.style = Paint.Style.STROKE
        mActiveIndicatorPaint.strokeWidth = mStrokeWidth

        // Create an oval which will be used later for drawing an arc inside this oval.
        val ovalLeft = mCenterX - mRadius
        val ovalTop = mCenterY - mRadius
        val ovalRight = mCenterX + mRadius
        val ovalBottom = mCenterY + mRadius
        val oval = RectF(ovalLeft, ovalTop, ovalRight, ovalBottom)

        val sweepAngle = mProgress * 3.6f
        canvas.drawArc(oval, PROGRESS_START_ANGLE, sweepAngle, false, mActiveIndicatorPaint)
    }
}