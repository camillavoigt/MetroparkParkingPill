package com.example.metroparkparkingpill

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class ColorCircle(context: Context, attr: AttributeSet?) : View(context, attr) {

    val availableColor = Paint().apply {
        style = Paint.Style.FILL
        color = Color.DKGRAY
    }

    val occupiedColor = Paint().apply {
        style = Paint.Style.FILL
        color = Color.YELLOW
    }

    val illegalColor = Paint().apply {
        style = Paint.Style.FILL
        color = Color.RED
    }

    var statusColor = availableColor

    /*  paintCircle = when {

     }
     */

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            val centerX = width / 2f
            val centerY = height / 2f
            drawCircle(centerX, centerY, 200f, statusColor)
        }
    }
}