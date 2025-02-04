package com.gamey.app

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import androidx.core.content.ContextCompat

class GradientCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    enum class GradientOrientation(val value: Int, val gradient: GradientDrawable.Orientation) {
        VERTICAL(0, GradientDrawable.Orientation.TOP_BOTTOM),
        HORIZONTAL(1, GradientDrawable.Orientation.LEFT_RIGHT)
    }

    private var startColor: Int
    private var endColor: Int
    private var strokeColor: Int
    private var solidColor: Int
    private var strokeWidth: Int
    private var cornerRadius: Float
    private var gradientOrientation: GradientOrientation = GradientOrientation.VERTICAL  // Default

    private var gradientDrawable: GradientDrawable
    private var isSolid = false  // Track current state

    init {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs, R.styleable.GradientCardView, 0, 0
        )
        try {
            startColor = typedArray.getColor(
                R.styleable.GradientCardView_startColor,
                ContextCompat.getColor(context, R.color.tab_inactive)
            )
            endColor = typedArray.getColor(
                R.styleable.GradientCardView_endColor,
                Color.WHITE
            )
            strokeColor = typedArray.getColor(
                R.styleable.GradientCardView_strokeColor,
                ContextCompat.getColor(context, R.color.black)
            )
            solidColor = typedArray.getColor(
                R.styleable.GradientCardView_solidColor,
                ContextCompat.getColor(context, R.color.tab_inactive)
            )
            strokeWidth = typedArray.getDimensionPixelSize(
                R.styleable.GradientCardView_strokeWidth, 2
            )
            cornerRadius = typedArray.getDimension(
                R.styleable.GradientCardView_cornerRadius, 20f
            )

            // Get gradient orientation (default is vertical)
            val orientationValue = typedArray.getInt(R.styleable.GradientCardView_gradientOrientation, 0)
            gradientOrientation = GradientOrientation.values().firstOrNull { it.value == orientationValue }
                ?: GradientOrientation.VERTICAL

        } finally {
            typedArray.recycle()
        }

        gradientDrawable = GradientDrawable(
            gradientOrientation.gradient,
            intArrayOf(startColor, endColor)
        ).apply {
            shape = GradientDrawable.RECTANGLE
            this.cornerRadius = this@GradientCardView.cornerRadius
            setStroke(strokeWidth, strokeColor)
        }

        background = gradientDrawable

        // Ensure the whole FrameLayout is clickable
        isClickable = true
        isFocusable = true

        setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (!isSolid) animateToSolidColor()
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    if (isSolid) animateToGradient()
                }
            }
            false
        }
    }

    private fun animateToSolidColor() {
        isSolid = true
        animateGradient(startColor, endColor, solidColor, solidColor, 150)
    }

    private fun animateToGradient() {
        isSolid = false
        animateGradient(solidColor, solidColor, startColor, endColor, 500)
    }

    private fun animateGradient(fromStart: Int, fromEnd: Int, toStart: Int, toEnd: Int, duration: Long) {
        ValueAnimator.ofFloat(0f, 1f).apply {
            this.duration = duration
            addUpdateListener { animator ->
                val fraction = animator.animatedValue as Float
                val animatedStartColor = blendColors(fromStart, toStart, fraction)
                val animatedEndColor = blendColors(fromEnd, toEnd, fraction)

                gradientDrawable.colors = intArrayOf(animatedStartColor, animatedEndColor)
                invalidate()
            }
            start()
        }
    }

    private fun blendColors(colorFrom: Int, colorTo: Int, ratio: Float): Int {
        val inverseRatio = 1f - ratio
        val alpha = (Color.alpha(colorFrom) * inverseRatio + Color.alpha(colorTo) * ratio).toInt()
        val red = (Color.red(colorFrom) * inverseRatio + Color.red(colorTo) * ratio).toInt()
        val green = (Color.green(colorFrom) * inverseRatio + Color.green(colorTo) * ratio).toInt()
        val blue = (Color.blue(colorFrom) * inverseRatio + Color.blue(colorTo) * ratio).toInt()
        return Color.argb(alpha, red, green, blue)
    }

    /** Set Gradient Orientation Dynamically */
    fun setGradientOrientation(orientation: GradientOrientation) {
        if (gradientOrientation != orientation) {
            gradientOrientation = orientation
            gradientDrawable.orientation = gradientOrientation.gradient
            invalidate()
        }
    }
}

