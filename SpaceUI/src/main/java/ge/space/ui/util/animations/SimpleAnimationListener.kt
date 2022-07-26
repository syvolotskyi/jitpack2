package com.space.components.animations

import android.view.animation.Animation
import javax.security.auth.callback.Callback

open class SimpleAnimationListener(val onEnd: (animation: Animation?) -> Unit) : Animation.AnimationListener {
    override fun onAnimationRepeat(animation: Animation?) {

    }

    override fun onAnimationEnd(animation: Animation?) {
        onEnd(animation)
    }

    override fun onAnimationStart(animation: Animation?) {

    }

}