package com.example.customview.layout.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

// Page 36
class CustomLayout(context: Context, attributeSet: AttributeSet) : ViewGroup(context, attributeSet) {

    override fun onLayout(p0: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val count = childCount
        var left = l + paddingLeft
        var top = t + paddingTop

        // keeps track of maximum row height
        var rowHeight = 0
        for (i in 0 until count) {
            val child = getChildAt(i);
            val childWidth = child.measuredWidth;
            val childHeight = child.measuredHeight;
            // if child fits in this row put it there
            if (left + childWidth < r - paddingRight) {
                child.layout(
                    left, top, left + childWidth, top +
                            childHeight
                );
                left += childWidth;
            } else {
                // otherwise put it on next row
                left = l + paddingLeft;
                top += rowHeight;
                rowHeight = 0;
            }
            // update maximum row height
            if (childHeight > rowHeight) rowHeight = childHeight;
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val count = childCount

        var rowHeight = 0
        var maxWidth = 0
        var maxHeight = 0
        var left = 0
        var top = 0

        for (i in 0 until count) {
            val child: View = getChildAt(i)
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
            val childWidth: Int = child.measuredWidth
            val childHeight: Int = child.measuredHeight
            // if child fits in this row put it there
            if (left + childWidth < width) {
                left += childWidth
            } else {
// otherwise put it on next row
                if (left > maxWidth) maxWidth = left
                left = 0
                top += rowHeight
                rowHeight = 0
            }
            // update maximum row height
            if (childHeight > rowHeight) rowHeight = childHeight
        }

        if (left > maxWidth) maxWidth = left;
        maxHeight = top + rowHeight;
        setMeasuredDimension(
            getMeasure(widthMeasureSpec, maxWidth),
            getMeasure(heightMeasureSpec, maxHeight)
        );

    }

    private fun getMeasure(spec: Int, desired: Int): Int {
        return when (MeasureSpec.getMode(spec)) {
            MeasureSpec.EXACTLY -> MeasureSpec.getSize(spec)
            MeasureSpec.AT_MOST -> Math.min(MeasureSpec.getSize(spec), desired)
            MeasureSpec.UNSPECIFIED -> desired
            else -> desired
        }
    }
}