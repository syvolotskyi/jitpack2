package ge.space.ui.components.controls

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import ge.space.ui.base.SPBaseView
import ge.space.ui.base.SPSetViewStyleInterface

class SPToggleIcon @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPBaseView(context, attrs, defStyleAttr), SPSetViewStyleInterface {



    override fun setViewStyle(newStyle: Int) {

    }
}