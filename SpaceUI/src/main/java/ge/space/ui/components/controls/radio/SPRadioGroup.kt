package ge.space.ui.components.controls.radio

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.core.view.children

class SPRadioGroup constructor(
    context: Context,
    attrs: AttributeSet?
) : RadioGroup(context, attrs) {

    override fun addView(child: View, index: Int, params: ViewGroup.LayoutParams?) {
        if (child is SPRadioButton) {
            child.setOnClickListener {
                setAllButtonsToUnselectedState()
                child.isChecked = true
            }
        }
        super.addView(child, index, params)
    }

    private fun setAllButtonsToUnselectedState() {
        children.forEach {
            if (it is SPRadioButton) {
                it.isChecked = false
            }
        }
    }
}
