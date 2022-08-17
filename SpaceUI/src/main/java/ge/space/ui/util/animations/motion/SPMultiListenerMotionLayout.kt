package ge.space.ui.util.animations.motion

import android.content.Context
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.constraintlayout.motion.widget.MotionLayout
import java.util.concurrent.CopyOnWriteArrayList

/**
 *Custom implementation of MotionLayout allows to store TransitionListeners
 */
class SPMultiListenerMotionLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MotionLayout(context, attrs, defStyleAttr) {

    private val listeners = CopyOnWriteArrayList<TransitionListener>()

    /**
     * Add a [TransitionListener] which will be called as appropriate.
     */
    override fun addTransitionListener(listener: TransitionListener) {
        super.addTransitionListener(listener)
        listeners.addIfAbsent(listener)
    }

    /**
     * Remove a previously added [TransitionListener].
     */
    override fun removeTransitionListener(listener: TransitionListener?): Boolean {
        listeners.remove(listener)
        return super.removeTransitionListener(listener)
    }


    override fun onFinishInflate() {
        super.onFinishInflate()
        super.setTransitionListener(object : TransitionListener {
            override fun onTransitionTrigger(motionLayout: MotionLayout, triggerId: Int, positive: Boolean, progress: Float) {
                listeners.forEach {
                    it.onTransitionTrigger(motionLayout, triggerId, positive, progress)
                }
            }

            override fun onTransitionStarted(motionLayout: MotionLayout, startId: Int, endId: Int) {
                listeners.forEach {
                    it.onTransitionStarted(motionLayout, startId, endId)
                }
            }

            override fun onTransitionChange(motionLayout: MotionLayout, startId: Int, endId: Int, progress: Float) {
                listeners.forEach {
                    it.onTransitionChange(motionLayout, startId, endId, progress)
                }
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
                listeners.forEach {
                    it.onTransitionCompleted(motionLayout, currentId)
                }
            }
        })
    }

    @Deprecated("Use addTransitionListener instead",
        ReplaceWith("throw IllegalArgumentException(\"Use addTransitionListener instead\")")
    )
    override fun setTransitionListener(listener: TransitionListener) {
        throw IllegalArgumentException("Use addTransitionListener instead")
    }
}