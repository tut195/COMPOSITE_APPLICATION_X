package com.example.customview.basic_rendering

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Region
import android.util.AttributeSet
import android.view.View

class CircularActivityIndicator @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : View(context, attributeSet) {

    private var foregroundPaint: Paint? = null
    private var selectedAngle = 0

    private var clipPath: Path? = null

    init {
        foregroundPaint = Paint()
        foregroundPaint?.color = DEFAULT_FG_COLOR
        foregroundPaint?.style = Paint.Style.FILL
        selectedAngle = 280
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        1) This will paint arc match_parent
//        canvas?.drawArc(
//            0F,
//            0F,
//            width.toFloat(),
//            height.toFloat(),
//            0F,
//            selectedAngle.toFloat(),
//            true,
//            foregroundPaint!!
//        )

//        //2)
//        // Lets create size with normal ratio
//        var circleSize = width
//        if (height < circleSize) circleSize = height
//        val horMargin = (width - circleSize) / 2
//        val verMargin = (height - circleSize) / 2
//        canvas!!.drawArc(
//            horMargin.toFloat(),
//            verMargin.toFloat(),
//            (horMargin + circleSize).toFloat(),
//            (verMargin + circleSize).toFloat(),
//            0f, selectedAngle.toFloat(), true, foregroundPaint!!
//        )
//        3)
        var circleSize = width
        if (height < circleSize) circleSize = height
        val horMargin = (width - circleSize) / 2
        val verMargin = (height - circleSize) / 2
// create a clipPath the first time
// create a clipPath the first time
        if (clipPath == null) {
            val clipWidth = (circleSize * 0.75).toInt()
            val clipX = (width - clipWidth) / 2
            val clipY = (height - clipWidth) / 2
            clipPath = Path()
            clipPath?.addArc(
                clipX.toFloat(),
                clipY.toFloat(),
                clipX.toFloat() + clipWidth,
                clipY.toFloat() + clipWidth,
                0F, 360F
            )
        }
        canvas!!.clipRect(0, 0, width, height)
        canvas.clipPath(clipPath!!, Region.Op.DIFFERENCE)
        canvas.drawArc(
            horMargin.toFloat(),
            verMargin.toFloat(),
            (horMargin + circleSize).toFloat(),
            (verMargin + circleSize).toFloat(),
            0F, selectedAngle.toFloat(), true, foregroundPaint!!
        )
    }

    companion object {
        private val DEFAULT_FG_COLOR = -0x10000
        private val DEFAULT_BG_COLOR = -0x5f5f60
    }
}