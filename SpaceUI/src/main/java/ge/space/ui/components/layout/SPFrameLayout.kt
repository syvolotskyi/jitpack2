package ge.space.ui.components.layout

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import ge.space.ui.base.SPBaseView

/**
 * Simple extended view from [SPBaseView] which has no any additional properties.
 */
class SPFrameLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPBaseView(context, attrs, defStyleAttr)