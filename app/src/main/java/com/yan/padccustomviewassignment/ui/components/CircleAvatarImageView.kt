package com.yan.padccustomviewassignment.ui.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet

class CircleAvatarImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatImageView(context, attrs) {
    private val path = Path()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var circleCenterX = 0.0f
    private var circleCenterY = 0.0f
    private var circleRadius = 0.0f
    private val borderWidth = 16.0f

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val size = measuredWidth.coerceAtMost(measuredHeight)
        setMeasuredDimension(size, size)
    }

    override fun onDraw(canvas: Canvas?) {
        cropCircle(canvas)
        super.onDraw(canvas)
        setStroke(canvas)
    }

    private fun cropCircle(canvas: Canvas?) {
        circleCenterX = width / 2f
        circleCenterY = height / 2f
        circleRadius = width / 2f

        path.addCircle(circleCenterX, circleCenterY, circleRadius, Path.Direction.CCW)
        canvas?.clipPath(path)
    }

    private fun setStroke(canvas: Canvas?) {
        paint.color = Color.WHITE
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderWidth

        canvas?.drawCircle(circleCenterX, circleCenterY, circleRadius - borderWidth / 2f, paint)
    }
}