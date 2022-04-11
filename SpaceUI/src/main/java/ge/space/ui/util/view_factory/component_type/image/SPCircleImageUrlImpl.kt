package ge.space.ui.util.view_factory.component_type.image

import android.content.Context
import android.view.View
import android.widget.ImageView
import ge.space.spaceui.R
import ge.space.ui.components.layout.SPFrameLayout
import ge.space.ui.util.extension.loadImageUrl
import ge.space.ui.util.extension.setHeight
import ge.space.ui.util.extension.setWidth
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.view.SPViewImpl

class SPCircleImageUrlImpl(context: Context) :
    SPViewImpl<SPViewData.SPCircleImageUrlData>(context) {
    override fun create(type: SPViewData.SPCircleImageUrlData): View {
        return SPFrameLayout(context).apply {
            setBaseViewStyle(R.style.shadow_empty)
            type.height?.let { this.setHeight(it) }
            type.width?.let { this.setWidth(it) }
            isCircle = true
            changeBorder(type.borderColor, type.borderSize.toFloat())
        }.also {
            val iView = ImageView(context)
            iView.scaleType = ImageView.ScaleType.CENTER_CROP
            context.loadImageUrl(type.url, iView)
            it.addView(iView)
        }
    }
}