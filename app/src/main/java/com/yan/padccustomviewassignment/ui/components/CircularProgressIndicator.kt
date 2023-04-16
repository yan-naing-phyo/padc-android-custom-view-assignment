package com.yan.padccustomviewassignment.ui.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import androidx.core.graphics.toRectF
import com.yan.padccustomviewassignment.R

class CircularProgressIndicator(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    companion object {
        private var DEFAULT_PROGRESS_BACKGROUND_COLOR = Color.parseColor("#FFE0B2")
        private var DEFAULT_PROGRESS_FOREGROUND_COLOR = Color.parseColor("#E65100")
        private const val DEFAULT_PROGRESS = 0
        private const val DEFAULT_PROGRESS_TEXT_SIZE = 14.0f
        private const val DEFAULT_PROGRESS_WIDTH = 4.0f
        private const val PROGRESS_START_ANGLE = 270.0f
        private const val OFFSET_LABEL = 30
    }

    // Circle
    private var size = 0
    private val circleCenterX: Float
        get() = size / 2f
    private val circleCenterY: Float
        get() = (size / 2f) - (textBounds.height() / 2)
    private val circleRadius: Float
        get() = textBounds.width().toFloat()
    private var progressWidth = 0.0f

    // Color
    private var progressBackgroundColor = DEFAULT_PROGRESS_BACKGROUND_COLOR
    private var progressForegroundColor = DEFAULT_PROGRESS_FOREGROUND_COLOR

    // Progress
    private var progress = 0
    private var progressUnit = ""
    private val progressText: String
        get() = "$progress$progressUnit"
    private var progressTextSize = 0.0f

    // Paint
    private lateinit var progressBackgroundPaint: Paint
    private lateinit var progressForegroundPaint: Paint
    private lateinit var textPaint: Paint

    // Progress text
    private val textBounds = Rect()

    init {
        setCustomAttributes(attrs)
        setProgressBackgroundPaint()
        setProgressForegroundPaint()
        setTextPaint()
    }

    private fun setTextPaint() {
        textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = progressForegroundColor
            textSize = progressTextSize

            textAlign = Paint.Align.CENTER
            style = Paint.Style.FILL
            getTextBounds(progressText, 0, progressText.length, textBounds)
        }
    }

    private fun setProgressForegroundPaint() {
        progressForegroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = progressForegroundColor
            style = Paint.Style.STROKE
            strokeWidth = progressWidth
        }
    }

    private fun setProgressBackgroundPaint() {
        progressBackgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = progressBackgroundColor
            style = Paint.Style.STROKE
            strokeWidth = progressWidth
        }
    }

    private fun setCustomAttributes(attrs: AttributeSet?) {
        context?.withStyledAttributes(attrs, R.styleable.CircularProgressIndicator) {
            progressBackgroundColor = getColor(
                R.styleable.CircularProgressIndicator_progressBackgroundColor,
                DEFAULT_PROGRESS_BACKGROUND_COLOR
            )
            progressForegroundColor = getColor(
                R.styleable.CircularProgressIndicator_progressForegroundColor,
                DEFAULT_PROGRESS_FOREGROUND_COLOR
            )
            progress = getInteger(R.styleable.CircularProgressIndicator_progress, DEFAULT_PROGRESS)
            progressUnit = getString(R.styleable.CircularProgressIndicator_progressUnit) ?: ""
            progressWidth = getDimension(
                R.styleable.CircularProgressIndicator_progressWidth,
                DEFAULT_PROGRESS_WIDTH
            )
            progressTextSize = getDimension(
                R.styleable.CircularProgressIndicator_progressTextSize,
                DEFAULT_PROGRESS_TEXT_SIZE
            )
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        size = measuredWidth.coerceAtMost(measuredHeight)
        setMeasuredDimension(size, size)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawProgressBackground(canvas)
        drawProgress(canvas)
        drawProgressText(canvas)
    }

    private fun drawProgress(canvas: Canvas?) {
        val oval = RectF(
            circleCenterX - circleRadius,
            circleCenterY - circleRadius,
            circleCenterX + circleRadius,
            circleCenterY + circleRadius
        )
        canvas?.drawArc(
            oval,
            PROGRESS_START_ANGLE,
            progress * 3.6f,
            false,
            progressForegroundPaint
        )
    }

    private fun drawProgressText(canvas: Canvas?) {
        canvas?.drawText(progressText, size / 2f, size / 2f, textPaint)
    }

    private fun drawProgressBackground(canvas: Canvas?) {
        canvas?.drawCircle(
            circleCenterX,
            circleCenterY,
            circleRadius,
            progressBackgroundPaint
        )
    }
}