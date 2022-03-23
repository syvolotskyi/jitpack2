package ge.space.ui.components.layout

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import ge.space.spaceui.R
import ge.space.ui.base.SPBaseView

/**
 * Simple extended view from [SPBaseView] which has no any additional properties.
 */
open class SPFrameLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPInputField
) : SPBaseView(context, attrs, defStyleAttr, defStyleRes)