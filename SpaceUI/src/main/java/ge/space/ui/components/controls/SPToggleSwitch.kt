package ge.space.ui.components.controls

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import com.google.android.material.switchmaterial.SwitchMaterial
import ge.space.ui.base.SPSetViewStyleInterface

class SPToggleSwitch @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : SwitchMaterial(context, attrs, defStyleAttr), SPSetViewStyleInterface {

    init {

    }

    override fun setViewStyle(newStyle: Int) {
        TODO("Not yet implemented")
    }

    private fun TypedArray.withStyledAttributes() {

    }

}