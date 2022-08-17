package ge.space.ui.util.view_factory.component_type.image

import android.content.Context
import android.view.View
import android.widget.FrameLayout
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
        return FrameLayout(context).apply {
            addView(SPFrameLayout(context).apply {
                setBaseViewStyle(R.style.shadow_empty)
                isCircle = true
                changeBorder(type.borderColor, type.borderSize.toFloat())
                type.params?.let {
                    it.height?.let { this.setHeight(it) }
                    it.width?.let { this.setWidth(it) }
                }
            }.also { frame ->
                val iView = ImageView(context)
                iView.scaleType = ImageView.ScaleType.CENTER_CROP
                context.loadImageUrl(type.url, iView)
                type.params?.let {
                    it.height?.let { iView.setHeight(it) }
                    it.width?.let { iView.setWidth(it) }
                }
                frame.addView(iView)

            })

            type.params?.let {
                it.height?.let { this.setHeight(it) }
                it.width?.let { this.setWidth(it) }
                setPadding(it.paddingStart, it.paddingTop, it.paddingEnd, it.paddingBottom)
            }
        }
    }
}