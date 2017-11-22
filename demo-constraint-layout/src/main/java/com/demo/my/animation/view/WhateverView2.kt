package com.demo.my.animation.view

import android.content.Context
import android.graphics.Canvas
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import android.util.Log

class WhateverView2 : AppCompatImageView {

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?,
                attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?,
                attrs: AttributeSet?,
                defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        // do something.
    }

    override fun onMeasure(widthMeasureSpec: Int,
                           heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.d("xyz", "Whatever2: onMeasure")
    }

    override fun onLayout(changed: Boolean,
                          left: Int,
                          top: Int,
                          right: Int,
                          bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.d("xyz", "Whatever2: onLayout")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.d("xyz", "Whatever2: onDraw")
    }
}
