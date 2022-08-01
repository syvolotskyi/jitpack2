package com.space.components.animations

import android.view.animation.Animation
import javax.security.auth.callback.Callback

open class AnimationEndListener(val onEnd: (animation: Animation?) -> Unit) :
    Animation.AnimationListener {
    override fun onAnimationRepeat(animation: Animation?) = Unit
    override fun onAnimationStart(animation: Animation?) = Unit
    override fun onAnimationEnd(animation: Animation?) = onEnd(animation)
}