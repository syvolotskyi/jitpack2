package ge.space.ui.util.view_factory.component_type.image

import android.content.Context
import android.widget.ImageView
import ge.space.ui.util.extension.fromResource
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.view.SPViewImpl

class SPImageResImpl(context: Context) : SPViewImpl<SPViewData.SPImageResourcesData>(context) {
    override fun create(type: SPViewData.SPImageResourcesData): ImageView {
        return ImageView(context).fromResource(type.res)
    }
}