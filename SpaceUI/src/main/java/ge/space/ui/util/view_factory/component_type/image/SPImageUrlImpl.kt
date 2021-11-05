package ge.space.ui.util.view_factory.component_type.image

import android.content.Context
import android.widget.ImageView
import ge.space.ui.util.extension.loadRoundImageUrl
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.view.SPViewImpl

class SPImageUrlImpl(context: Context) : SPViewImpl<SPViewData.SPImageUrlData>(context) {
    override fun create(type: SPViewData.SPImageUrlData): ImageView {
        return ImageView(context).apply {
            context.loadRoundImageUrl(type.url, this, type.roundedCorners.toInt())
        }
    }
}