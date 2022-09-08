package ge.space.ui.components.amount

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import ge.space.spaceui.R
import ge.space.ui.base.SPViewStyling

class SPAmountView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPEmptyStateBase
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes), SPViewStyling {

}