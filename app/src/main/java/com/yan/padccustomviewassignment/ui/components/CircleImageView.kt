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

class CircleImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatImageView(context, attrs) {
    companion object {
        private const val DEFAULT_STROKE_COLOR = Color.WHITE
        private const val DEFAULT_STROKE_WIDTH = 1.0f
    }

    // Paint variables
    private val mBackgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mStrokePaint = Paint(Paint.ANTI_ALIAS_FLAG)

    // Circle coordinates
    private var mCenterX = 0f
    private var mCenterY = 0f
    private var mRadius = 0f

    private var mStrokeColor = 0
    private var mStrokeWidth = 0f

    private val path = Path()

    init {
        context.withStyledAttributes(attrs, R.styleable.CircleAvatarImageView) {
            mStrokeWidth =
                getDimension(
                    R.styleable.CircleAvatarImageView_strokeWidth,
                    context.dpToPx(DEFAULT_STROKE_WIDTH)
                )
            mStrokeColor =
                getColor(R.styleable.CircleAvatarImageView_strokeColor, DEFAULT_STROKE_COLOR)

        }
    }

    override fun onDraw(canvas: Canvas) {
        mRadius = (width.coerceAtMost(height)) / 2f
        mCenterX = width / 2f
        mCenterY = height / 2f

        drawBackground(canvas)
        cropImageInCircle(canvas)
        super.onDraw(canvas)

        drawStrokeAroundCircleImage(canvas)
    }

    private fun drawBackground(canvas: Canvas) {
        // Configure background paint.
        mBackgroundPaint.color = backgroundTintList?.defaultColor ?: Color.WHITE
        mBackgroundPaint.style = Paint.Style.FILL

        canvas.drawCircle(mCenterX, mCenterY, mRadius, mBackgroundPaint)
    }

    private fun cropImageInCircle(canvas: Canvas) {
        path.addCircle(mCenterX, mCenterY, mRadius, Path.Direction.CCW)
        canvas.clipPath(path)
    }

    private fun drawStrokeAroundCircleImage(canvas: Canvas) {
        // Configure stroke paint.
        mStrokePaint.color = mStrokeColor
        mStrokePaint.style = Paint.Style.STROKE
        mStrokePaint.strokeWidth = mStrokeWidth

        val radius = mRadius - (mStrokeWidth / 2f)

        canvas.drawCircle(mCenterX, mCenterY, radius, mStrokePaint)
    }
}