package ge.space.ui.components.accordion.expansion

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import ge.space.spaceui.R
import ge.space.ui.components.accordion.expansion.SPExpansionLayout.State.*

class SPExpansionLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    var duration = DEFAULT_DURATION
    private var parallax = 0f
    private var expansion = 0f

    /**
     * Get expansion state
     *
     * @return one of [State]
     */
    var state = COLLAPSED
        private set
    private var animator: ValueAnimator? = null
    private var listener: OnExpansionUpdateListener? = null

    init {
        getContext().obtainStyledAttributes(attrs, R.styleable.SPExpansionLayout).apply {
            duration = getInt(R.styleable.SPExpansionLayout_duration, DEFAULT_DURATION)
            expansion = if (getBoolean(
                    R.styleable.SPExpansionLayout_expanded,
                    false
                )
            ) 1f else 0f
            parallax = getFloat(R.styleable.SPExpansionLayout_parallax, 1f)
            recycle()
            state = if (expansion == 0f) COLLAPSED else EXPANDED
            setParallax(parallax)
        }
    }

    override fun onSaveInstanceState(): Parcelable {
        val superState = super.onSaveInstanceState()
        val bundle = Bundle()
        expansion = if (isExpanded) 1f else 0f
        bundle.putFloat(KEY_EXPANSION, expansion)
        bundle.putParcelable(KEY_SUPER_STATE, superState)
        return bundle
    }

    override fun onRestoreInstanceState(parcelable: Parcelable) {
        val bundle = parcelable as Bundle
        expansion = bundle.getFloat(KEY_EXPANSION)
        state = if (expansion == 1f) EXPANDED else COLLAPSED
        val superState = bundle.getParcelable<Parcelable>(KEY_SUPER_STATE)
        super.onRestoreInstanceState(superState)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = measuredWidth
        val height = measuredHeight
        visibility = if (expansion == 0f && height == 0) GONE else VISIBLE
        val expansionDelta = height - Math.round(height * expansion)
        if (parallax > 0) {
            val parallaxDelta = expansionDelta * parallax
            for (i in 0 until childCount) {
                val child = getChildAt(i)

                child.translationY = -parallaxDelta

            }
        }
        setMeasuredDimension(width, height - expansionDelta)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        if (animator != null) {
            animator!!.cancel()
        }
        super.onConfigurationChanged(newConfig)
    }

    /**
     * Convenience method - same as calling setExpanded(expanded, true)
     */
    var isExpanded: Boolean
        get() = state == EXPANDING || state == EXPANDED
        set(expand) {
            setExpanded(expand, true)
        }

    @JvmOverloads
    fun toggle(animate: Boolean = true) {
        if (isExpanded) {
            collapse(animate)
        } else {
            expand(animate)
        }
    }

    @JvmOverloads
    fun expand(animate: Boolean = true) {
        setExpanded(true, animate)
    }

    @JvmOverloads
    fun collapse(animate: Boolean = true) {
        setExpanded(false, animate)
    }

    fun setExpanded(expand: Boolean, animate: Boolean) {
        if (expand == isExpanded) {
            return
        }
        val targetExpansion = if (expand) 1 else 0
        if (animate) {
            animateSize(targetExpansion)
        } else {
            setExpansion(targetExpansion.toFloat())
        }
    }

    fun setExpansion(expansion: Float) {
        if (this.expansion == expansion) {
            return
        }

        // Infer state from previous value
        val delta = expansion - this.expansion
        when {
            expansion == 0f -> { state = COLLAPSED }
            expansion == 1f -> { state = EXPANDED }
            delta < 0 -> { state = COLLAPSING }
            delta > 0 -> { state = EXPANDING }
        }
        visibility = if (state == COLLAPSED) GONE else VISIBLE
        this.expansion = expansion
        requestLayout()
        if (listener != null) {
            listener!!.onExpansionUpdate(expansion, state)
        }
    }

    fun setParallax(parallax: Float) {
        // Make sure parallax is between 0 and 1
        var parallax = parallax
        parallax = 1f.coerceAtMost(0f.coerceAtLeast(parallax))
        this.parallax = parallax
    }

    fun setOnExpansionUpdateListener(listener: OnExpansionUpdateListener?) {
        this.listener = listener
    }

    private fun animateSize(targetExpansion: Int) {
        animator?.let {
            it.cancel()
            animator = null
        }
        animator = ValueAnimator.ofFloat(expansion, targetExpansion.toFloat()).apply {
            interpolator = interpolator
            duration = duration
            addUpdateListener { valueAnimator ->
                setExpansion(valueAnimator.animatedValue as Float)
            }
            addListener(ExpansionListener(targetExpansion))
            start()
        }
    }

    interface OnExpansionUpdateListener {
        /**
         * Callback for expansion updates
         *
         * @param expansionFraction Value between 0 (collapsed) and 1 (expanded) representing the the expansion progress
         * @param state             One of [State] repesenting the current expansion state
         */
        fun onExpansionUpdate(expansionFraction: Float, state: State)
    }

    private inner class ExpansionListener(private val targetExpansion: Int) :
        Animator.AnimatorListener {
        private var canceled = false
        override fun onAnimationStart(animation: Animator) {
            state = if (targetExpansion == 0) COLLAPSING else EXPANDING
        }

        override fun onAnimationEnd(animation: Animator) {
            if (!canceled) {
                state = if (targetExpansion == 0) COLLAPSED else EXPANDED
                setExpansion(targetExpansion.toFloat())
            }
        }

        override fun onAnimationCancel(animation: Animator) {
            canceled = true
        }

        override fun onAnimationRepeat(animation: Animator) {}
    }

    companion object {
        const val KEY_SUPER_STATE = "super_state"
        const val KEY_EXPANSION = "expansion"
        private const val DEFAULT_DURATION = 300
    }

    enum class State {
        COLLAPSED,
        COLLAPSING,
        EXPANDING,
        EXPANDED,
    }
}