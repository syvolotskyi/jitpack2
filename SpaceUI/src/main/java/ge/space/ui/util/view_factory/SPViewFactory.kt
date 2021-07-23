package ge.space.ui.util.view_factory

import android.content.Context
import android.view.View
import android.widget.ImageView
import ge.space.ui.util.extension.fromResource
import ge.space.ui.util.extension.loadImageUrl
import ge.space.ui.util.view_factory.component_type.chip.*

interface SPViewFactory {
    companion object {
        /**
         * Returns a view object.
         */
        fun SPViewData.createView(context: Context): View {
            return when (this) {
                is SPViewData.SPImageResourcesData -> ImageView(context).fromResource(res)
                is SPViewData.SPImageDefaultResourcesData -> ImageView(context).fromResource(res)
                is SPViewData.SPImageUrlData -> ImageView(context).apply {
                    context.loadImageUrl(url, this)
                }
                is SPViewData.SPrimaryChipData -> SPPrimaryChipIconImpl(context).create(this)
                is SPViewData.SPSecondaryChipData -> SPSecondaryChipIconImpl(context).create(this)
                is SPViewData.SPDigitalChipData -> SPDigitalChipIconImpl(context).create(this)
                is SPViewData.SPEmptyChipData -> SPEmptyChipIconImpl(context).create(this)
                is SPViewData.SPChipData -> SPChipIconImpl(context).create(this)
            }
        }
    }
}

