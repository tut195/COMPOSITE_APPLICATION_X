package com.example.customview.view.custom

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.customview.R

class OwnCustomView(context: Context, attributeSet: AttributeSet?) : View(context, attributeSet) {

    private var topLeftColor = DEFAULT_FILL_COLOR
    private var bottomLeftColor = DEFAULT_FILL_COLOR
    private var topRightColor = DEFAULT_FILL_COLOR
    private var bottomRightColor = DEFAULT_FILL_COLOR
    private var needsUpdate = false

    constructor(context: Context) : this(context, null)

    private var backgroundPaint: Paint = Paint().apply {
        color = Color.RED
        style = Paint.Style.FILL
    }
    private val fillColor: Int

    init {
        val ta: TypedArray = context.theme.obtainStyledAttributes(attributeSet, R.styleable.OwnCustomView, 0, 0)
        fillColor = try {
            ta.getColor(R.styleable.OwnCustomView_fillColor, DEFAULT_FILL_COLOR)
        } finally {
            ta.recycle()
        }

        backgroundPaint = Paint()
        backgroundPaint.style = Paint.Style.FILL
        setFillColor(fillColor)
    }


    private fun setFillColor(fillColor: Int) {
        backgroundPaint.color = fillColor
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawRect(0f, 0f, width.toFloat(), height.toFloat(), backgroundPaint)
        super.onDraw(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        Log.d("TAG", "width spec: " + MeasureSpec.toString(widthMeasureSpec))
//        Log.d("TAG", "height spec: " + MeasureSpec.toString(heightMeasureSpec))

        // Add gradient
        if (needsUpdate) {
            val colors = intArrayOf(
                topLeftColor, topRightColor,
                bottomRightColor, bottomLeftColor
            )
            val lg = LinearGradient(0f, 0f, width.toFloat(), height.toFloat(), colors, null, Shader.TileMode.CLAMP)
            backgroundPaint.shader = lg
            needsUpdate = false
        }


        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//
//        val width = getMeasurementSize(widthMeasureSpec, DEFAULT_SIZE)
//        val height = getMeasurementSize(heightMeasureSpec, DEFAULT_SIZE)
//        setMeasuredDimension(width, height)

    }

    private fun getMeasurementSize(measureSpec: Int, defaultSize: Int): Int {
        val mode = MeasureSpec.getMode(measureSpec)
        val size = MeasureSpec.getSize(measureSpec)
        return when (mode) {
            MeasureSpec.EXACTLY -> size
            MeasureSpec.AT_MOST -> Math.min(defaultSize, size)
            MeasureSpec.UNSPECIFIED -> defaultSize
            else -> defaultSize
        }
    }

    fun setTopLeftColor(topLeftColor: Int) {
        this.topLeftColor = topLeftColor
        needsUpdate = true
    }

    fun setBottomLeftColor(bottomLeftColor: Int) {
        this.bottomLeftColor = bottomLeftColor
        needsUpdate = true
    }

    fun setTopRightColor(topRightColor: Int) {
        this.topRightColor = topRightColor
        needsUpdate = true
    }

    fun setBottomRightColor(bottomRightColor: Int) {
        this.bottomRightColor = bottomRightColor
        needsUpdate = true
    }


    companion object {
        private val DEFAULT_SIZE = 100;
        private val DEFAULT_FILL_COLOR = Color.BLUE

        // Пример с паттернм Строитель!
        // В кнге это страница 32
        class Builder(private val context: Context) {
            private var topLeftColor = DEFAULT_FILL_COLOR
            private var topRightColor = DEFAULT_FILL_COLOR
            private var bottomLeftColor = DEFAULT_FILL_COLOR
            private var bottomRightColor = DEFAULT_FILL_COLOR

            fun topLeftColor(topLeftColor: Int): Builder {
                this.topLeftColor = topLeftColor
                return this
            }

            // Continue adding fields by Builder Pattern

            fun build(): OwnCustomView = OwnCustomView(context)


        }


    }


}

