package ge.space.ui.components.controls.radio

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.core.view.children
import ge.space.extensions.onClick

/**
 *  Extended view from [RadioGroup] and add the possibility to work with SPRadioButton.
 */
class SPRadioGroup constructor(
    context: Context,
    attrs: AttributeSet?
) : RadioGroup(context, attrs) {

    private var onCheckedChangeListener: OnCheckedChangeListener? = null

    override fun addView(child: View, index: Int, params: ViewGroup.LayoutParams?) {
        if (child is SPRadioButton) {
            child.onClick {
                setAllButtonsToUnselectedState()
                child.isChecked = true
                onCheckedChangeListener?.onCheckedChanged(this@SPRadioGroup, child.id)
            }
        } else throw UnsupportedOperationException("Use a SPRadioButton only as a child for SPRadioGroup")
        super.addView(child, index, params)
    }

    override fun setOnCheckedChangeListener(listener: OnCheckedChangeListener) {
        onCheckedChangeListener = listener
    }

    private fun setAllButtonsToUnselectedState() {
        children.forEach {
            if (it is SPRadioButton) {
                it.isChecked = false
            }
        }
    }
}
