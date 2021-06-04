package ge.space.extensions

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.os.Vibrator
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.annotation.AnimRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

inline fun View.animate(@AnimRes resId: Int, crossinline onComplete: (() -> Unit)) {
    val animation = AnimationUtils.loadAnimation(context, resId)
    animation.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation?) {

        }

        override fun onAnimationEnd(animation: Animation?) {
            onComplete.invoke()
        }

        override fun onAnimationStart(animation: Animation?) {

        }
    })
    this.startAnimation(animation)
}

inline fun ImageView.animateImageChange(
    @AnimRes outResId: Int,
    @AnimRes inResId: Int,
    @DrawableRes newImageRes: Int,
    crossinline onComplete: (() -> Unit)
) {
    val inAnimation = AnimationUtils.loadAnimation(context, inResId)
    val outAnimation = AnimationUtils.loadAnimation(context, outResId)
    outAnimation.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation?) {

        }

        override fun onAnimationEnd(animation: Animation?) {
            setImageResource(newImageRes)
            this@animateImageChange.startAnimation(inAnimation)
        }

        override fun onAnimationStart(animation: Animation?) {

        }
    })

    inAnimation.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation?) {

        }

        override fun onAnimationEnd(animation: Animation?) {

            onComplete.invoke()
        }

        override fun onAnimationStart(animation: Animation?) {

        }
    })
    this.startAnimation(outAnimation)
}

@SuppressLint("MissingPermission")
fun View.shakeAnimation() {
    ObjectAnimator.ofFloat(
        this,
        "translationX",
        0.0f,
        25.0f,
        -25.0f,
        25.0f,
        -25.0f,
        15.0f,
        -15.0f,
        6.0f,
        -6.0f,
        0.0f
    )
        .setDuration(1000).start()
    (context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).vibrate(
        longArrayOf(
            0,
            100,
            1000
        ), -1
    )
}

fun View.tintAnimation(startColorRes: Int, endColorRes: Int) {
    val backgroundColorAnimator = ObjectAnimator.ofObject(
        this,
        "backgroundColor",
        ArgbEvaluator(),
        ContextCompat.getColor(this.context, startColorRes),
        ContextCompat.getColor(this.context, endColorRes)
    )
    backgroundColorAnimator.duration = 320
    backgroundColorAnimator.start()
}

fun View.playAnimation(
    @AnimRes animResId: Int,
    onAnimationRepeat: () -> Unit = {},
    onAnimationStart: () -> Unit = {},
    onAnimationEnd: () -> Unit = {}) = with(AnimationUtils.loadAnimation(context, animResId)) {
    setAnimationListener(AnimationListener(onAnimationRepeat, onAnimationStart, onAnimationEnd))
    startAnimation(this)
}

fun View.rotationAnimation(from: Float, to: Float, duration: Long = 100L, startDelay: Long = 0){
    this.rotation = from
    runDelayed(startDelay){
        val viewObjectAnimator = ObjectAnimator.ofFloat(this, "rotation", from, to)
        viewObjectAnimator.duration = duration
        viewObjectAnimator.start()
    }
}

private class AnimationListener(
    private val onAnimationRepeat: () -> Unit,
    private val onAnimationStart: () -> Unit,
    private val onAnimationEnd: () -> Unit
) : Animation.AnimationListener {
    override fun onAnimationRepeat(p0: Animation?) = onAnimationRepeat()
    override fun onAnimationStart(p0: Animation?) = onAnimationStart()
    override fun onAnimationEnd(p0: Animation?) = onAnimationEnd()
}