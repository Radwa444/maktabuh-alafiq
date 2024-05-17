package com.example.maktabuhalafiq

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import kotlin.random.Random


class CustomArcView@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paints = List(5) {
        Paint().apply {
            color = Random.nextInt(0xFF000000.toInt(), 0xFFFFFFFF.toInt())
            style = Paint.Style.FILL
        }
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val left = 50f
        val top = 50f
        val right = width - 50f
        val bottom = height - 50f

        val rectF = RectF(left, top, right, bottom)

        var startAngle = 0f

        paints.forEach { paint ->
            val sweepAngle = Random.nextFloat() * 120f
            canvas.drawArc(rectF, startAngle, sweepAngle, true, paint)
            startAngle += sweepAngle
        }
    }
}