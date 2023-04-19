package com.yan.padccustomviewassignment.ui.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import androidx.core.content.withStyledAttributes
import com.yan.padccustomviewassignment.R
import com.yan.padccustomviewassignment.utils.dpToPx
import kotlin.math.cos
import kotlin.math.sin

class CircleImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatImageView(context, attrs) {
    companion object {
        private const val DEFAULT_STROKE_COLOR = Color.WHITE
        private const val DEFAULT_STROKE_WIDTH = 1.0f

        private val ACTIVE_COLOR = Color.argb(255, 95, 189, 158)
        private const val ACTIVE_STROKE_WIDTH = 10.0f
        private const val ACTIVE_ANGLE_IN_DEGREE = 45
    }

    // Paint variables
    private val mBackgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mStrokePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mActivePaint = Paint(Paint.ANTI_ALIAS_FLAG)

    // Circle coordinates
    private var mCenterX = 0f
    private var mCenterY = 0f
    private var mRadius = 0f

    // Active circle coordinates
    private var mActiveCenterX = 0f
    private var mActiveCenterY = 0f
    private var mActiveRadius = 0f

    private var mStrokeColor = 0
    private var mStrokeWidth = 0f
    private var mShowActive = false

    private val path = Path()

    init {
        context.withStyledAttributes(attrs, R.styleable.CircleImageView) {
            mStrokeWidth =
                getDimension(
                    R.styleable.CircleImageView_strokeWidth,
                    context.dpToPx(DEFAULT_STROKE_WIDTH)
                )
            mStrokeColor =
                getColor(R.styleable.CircleImageView_strokeColor, DEFAULT_STROKE_COLOR)
            mShowActive = getBoolean(R.styleable.CircleImageView_showActive, false)
        }
    }

    override fun onDraw(canvas: Canvas) {
        mRadius = (width.coerceAtMost(height)) / 2f
        mCenterX = width / 2f
        mCenterY = height / 2f

        mActiveRadius = mRadius / 5f
        mActiveCenterX =
            (mCenterX + mRadius * cos(ACTIVE_ANGLE_IN_DEGREE * Math.PI / 180)).toFloat()
        mActiveCenterY =
            (mCenterY + mRadius * sin(ACTIVE_ANGLE_IN_DEGREE * Math.PI / 180)).toFloat()

        drawBackground(canvas)
        drawCircularClipping(canvas)
        super.onDraw(canvas)

        drawImageCircularStroke(canvas)
        drawActiveCircle(canvas)
        drawActiveStroke(canvas)
    }

    private fun drawActiveStroke(canvas: Canvas) {
        // Configure stroke paint.
        mStrokePaint.color = Color.WHITE
        mStrokePaint.style = Paint.Style.STROKE
        mStrokePaint.strokeWidth = ACTIVE_STROKE_WIDTH

        val radius = mActiveRadius - (ACTIVE_STROKE_WIDTH / 2f)

        canvas.drawCircle(mActiveCenterX, mActiveCenterY, radius, mStrokePaint)
    }

    private fun drawActiveCircle(canvas: Canvas) {
        if (mShowActive) {
            mActivePaint.color = ACTIVE_COLOR
            mActivePaint.style = Paint.Style.FILL
            canvas.drawCircle(
                mActiveCenterX,
                mActiveCenterY,
                mActiveRadius,
                mActivePaint
            )
        }
    }

    private fun drawBackground(canvas: Canvas) {
        // Configure background paint.
        mBackgroundPaint.color = backgroundTintList?.defaultColor ?: Color.WHITE
        mBackgroundPaint.style = Paint.Style.FILL

        canvas.drawCircle(mCenterX, mCenterY, mRadius, mBackgroundPaint)
    }

    private fun drawCircularClipping(canvas: Canvas) {
        path.addCircle(mCenterX, mCenterY, mRadius, Path.Direction.CCW)

        if (mShowActive) {
            path.addCircle(
                mActiveCenterX,
                mActiveCenterY,
                mActiveRadius,
                Path.Direction.CCW
            )
        }

        canvas.clipPath(path)
    }

    private fun drawImageCircularStroke(canvas: Canvas) {
        // Configure stroke paint.
        mStrokePaint.color = mStrokeColor
        mStrokePaint.style = Paint.Style.STROKE
        mStrokePaint.strokeWidth = mStrokeWidth

        val radius = mRadius - (mStrokeWidth / 2f)

        canvas.drawCircle(mCenterX, mCenterY, radius, mStrokePaint)
    }

    fun setStrokeWidth(strokeWidth: Float) {
        mStrokeWidth = strokeWidth
        invalidate()
    }
}