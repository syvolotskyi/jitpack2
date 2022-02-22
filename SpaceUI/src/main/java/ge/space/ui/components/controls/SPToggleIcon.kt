package ge.space.ui.components.controls

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.core.content.withStyledAttributes
import com.google.android.material.button.MaterialButton
import ge.space.spaceui.R

/**
 * [SPToggleIcon] is a checkable button component which extends from [MaterialButton]
 * As a [defStyleAttr] we created R.attr.spToggleIconStyle
 */
class SPToggleIcon @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = R.attr.spToggleIconStyle,
) :MaterialButton(context, attrs, defStyleAttr){

    init {
        context.withStyledAttributes(attrs, R.styleable.CompoundButton, defStyleAttr) {
            isChecked = getBoolean(R.styleable.SPToggleIcon_checked,false)
        }
    }

}
