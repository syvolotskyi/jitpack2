package ge.space.ui.util.view_factory.component_type.image

import android.content.Context
import android.widget.ImageView
import ge.space.ui.util.extension.loadImageUrl
import ge.space.ui.util.extension.loadRoundImageUrl
import ge.space.ui.util.extension.setHeight
import ge.space.ui.util.extension.setWidth
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.view.SPViewImpl

class SPImageUrlImpl(context: Context) : SPViewImpl<SPViewData.SPImageUrlData>(context) {
    override fun create(type: SPViewData.SPImageUrlData): ImageView {
        return ImageView(context).apply {
            if (type.roundedCorners > 0f)
                context.loadRoundImageUrl(type.url, this, type.roundedCorners.toInt())
            else
                context.loadImageUrl(type.url, this)

            type.params?.let {
                it.height?.let { this.setHeight(it) }
                it.width?.let { this.setWidth(it) }
                setPadding(it.paddingStart, it.paddingTop, it.paddingEnd, it.paddingBottom)
            }
        }
    }
}