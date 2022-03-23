package ge.space.ui.util.view_factory.component_type.image

import android.content.Context
import android.widget.ImageView
import ge.space.extensions.setHeight
import ge.space.extensions.setWidth
import ge.space.extensions.tintColor
import ge.space.ui.util.extension.fromResource
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.view.SPViewImpl

class SPImageResImpl(context: Context) : SPViewImpl<SPViewData.SPImageResourcesData>(context) {
    override fun create(type: SPViewData.SPImageResourcesData): ImageView {
        return ImageView(context).fromResource(type.res).apply {
            type.tintColor?.let { this.tintColor = it }
            type.backgroundColor?.let { this.setBackgroundColor(it) }
            type.params?.let {
                it.height?.let { this.setHeight(it) }
                it.width?.let { this.setWidth(it) }
                setPadding(it.paddingStart, it.paddingTop, it.paddingEnd, it.paddingBottom)
            }
        }
    }
}