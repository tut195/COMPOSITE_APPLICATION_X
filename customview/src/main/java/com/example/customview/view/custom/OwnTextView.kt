package com.example.customview.view.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView


class OwnTextView(context: Context, attributeSet: AttributeSet) : AppCompatTextView(context, attributeSet) {

    private val backgroundPaint: Paint = Paint().apply {
        color = Color.RED
        style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawRect(0f, 0f, width.toFloat(), height.toFloat(), backgroundPaint)
        super.onDraw(canvas)
    }

}