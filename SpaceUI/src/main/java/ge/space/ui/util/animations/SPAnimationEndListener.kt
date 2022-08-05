package com.space.components.animations

import android.view.animation.Animation
import javax.security.auth.callback.Callback

/**
 * Custom implementation of  Animation.AnimationListener allows use only onAnimationEnd action.
 * onAnimationRepeat and onAnimationStart are empty by default
 *
 * @param onEnd [(animation: Animation?) -> Unit] calls when animation ended
 */
open class SPAnimationEndListener(val onEnd: (animation: Animation?) -> Unit) :
    Animation.AnimationListener {
    override fun onAnimationRepeat(animation: Animation?) = Unit
    override fun onAnimationStart(animation: Animation?) = Unit
    override fun onAnimationEnd(animation: Animation?) = onEnd(animation)
}