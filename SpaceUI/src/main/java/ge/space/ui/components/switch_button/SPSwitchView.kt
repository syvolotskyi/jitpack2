package ge.space.ui.components.switch_button

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.appcompat.widget.SwitchCompat
import ge.space.spaceui.R

class SPSwitchView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        @AttrRes defStyleAttr: Int = 0
) : SwitchCompat(context, attrs, defStyleAttr)