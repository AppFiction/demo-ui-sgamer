package com.gamey.app

import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation

object ViewAnimator {

    fun showFromBottom(view: View) {
        if (view.visibility == View.VISIBLE) return

        view.visibility = View.VISIBLE
        val animate = TranslateAnimation(0f, 0f, view.height.toFloat(), 0f).apply {
            duration = 300
            fillAfter = true
        }
        view.startAnimation(animate)
    }

    fun hideToBottom(view: View) {
        if (view.visibility == View.GONE) return

        val animate = TranslateAnimation(0f, 0f, 0f, view.height.toFloat()).apply {
            duration = 300
            fillAfter = true
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {}
                override fun onAnimationEnd(animation: Animation?) {
                    view.visibility = View.GONE
                }
                override fun onAnimationRepeat(animation: Animation?) {}
            })
        }
        view.startAnimation(animate)
    }
}
