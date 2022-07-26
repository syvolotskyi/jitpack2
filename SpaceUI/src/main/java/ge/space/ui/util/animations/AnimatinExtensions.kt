package com.space.components.animations

import android.content.res.Resources
import android.view.View
import android.view.ViewPropertyAnimator
import android.view.animation.AnimationUtils
import androidx.annotation.IntegerRes
import ge.space.ui.util.extension.hide
import ge.space.ui.util.extension.runDelayed
import ge.space.ui.util.extension.show


fun ViewPropertyAnimator.setDurationRes(
    resources: Resources,
    @IntegerRes int: Int
): ViewPropertyAnimator {
    duration = resources.getInteger(int).toLong()
    return this
}


fun View.fadeInAnimation(
    duration: Long,
    onAnimationEnd: (() -> Unit)? = null
) {
    this.alpha = 0f
    scaleX = 1f
    scaleY = 1f
    show()
    this.animate()
        .withEndAction {
            this.show()
            onAnimationEnd?.invoke()
        }
        .alpha(1f)
        .duration = duration
}

fun View.fadeInAnimationDelayed(
    duration: Long,
    delay: Long,
    onAnimationEnd: (() -> Unit)? = null
) {
    runDelayed(delay) {
        fadeInAnimation(duration, onAnimationEnd)
    }
}

fun View.fadeOutAnimation(
    duration: Long,
    onAnimationEnd: (() -> Unit)? = null
) {
    this.alpha = 1f
    this.animate()
        .withEndAction {
            this.hide()
            onAnimationEnd?.invoke()
        }
        .alpha(0f)
        .duration = duration
}

fun View.zoomInAnimation(
    duration: Long,
    onAnimationEnd: (() -> Unit)? = null
) {
    this.alpha = 0f
    scaleX = 0f
    scaleY = 0f
    show()
    this.animate()
        .scaleX(1f)
        .scaleY(1f)
        .withEndAction {
            this.show()
            onAnimationEnd?.invoke()
        }
        .alpha(1f)
        .duration = duration
}

fun View.zoomOutAnimation(
    duration: Long,
    onAnimationEnd: (() -> Unit)? = null
) {
    this.alpha = 1f
    scaleX = 1f
    scaleY = 1f
    this.animate()
        .scaleX(0f)
        .scaleY(0f)
        .withEndAction {
            this.hide()
            onAnimationEnd?.invoke()
        }
        .alpha(0f)
        .duration = duration
}