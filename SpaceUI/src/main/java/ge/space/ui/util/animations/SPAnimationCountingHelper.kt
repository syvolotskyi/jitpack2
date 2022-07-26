package com.space.components.animations

import android.view.animation.AccelerateInterpolator
import android.view.animation.Interpolator
import android.widget.TextView

class SPAnimationCountingHelper(
    startingValue: Long, finalValue: Long, duration: Long,
    val view: TextView,
    val callback: (Long) -> Unit
) : Runnable {
    private val mDuration: Long = duration
    private val mStartingValue: Long = startingValue
    private val mFinalValue: Long = finalValue
    private val mValueRange: Long = mFinalValue - mStartingValue
    private var mProgressTime: Long = 0
    private var mLastUpdateTime: Long = 0
    private val mInterpolator: Interpolator = AccelerateInterpolator()
    override fun run() {
        val now = System.currentTimeMillis()
        mProgressTime += now - mLastUpdateTime
        var shouldSchedule = true
        if (mProgressTime >= mDuration) {
            mProgressTime = mDuration
            shouldSchedule = false
        }
        callback.invoke(currentValue)
        if (shouldSchedule) {
            view.postOnAnimation(this)
        }
    }

    private val currentValue: Long
        private get() = if (mProgressTime >= mDuration) {
            mFinalValue
        } else (mStartingValue + mInterpolator.getInterpolation(
            mProgressTime.toFloat() / mDuration.toFloat()
        ) * mValueRange).toLong()

    init {
        mLastUpdateTime = System.currentTimeMillis()
    }
}