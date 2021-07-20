package ge.space.ui.components.view

import android.content.Context
import android.view.View
import android.widget.ImageView
import ge.space.ui.components.view.component_type.chip.SPDigitalChipIconImpl
import ge.space.ui.components.view.component_type.chip.SPEmptyChipIconImpl
import ge.space.ui.components.view.component_type.chip.SPPrimaryChipIconImpl
import ge.space.ui.components.view.component_type.chip.SPSecondaryChipIconImpl
import ge.space.ui.util.extension.fromResource
import ge.space.ui.util.extension.loadImageUrl

interface SPViewFactory {
    companion object {
        /**
         * Returns a view object.
         *
         */
        fun SPViewData.createView(context: Context): View {
            return when (this) {
                is SPViewData.SPImageResourcesData -> ImageView(context).fromResource(res)
                is SPViewData.SPImageDefaultResourcesData -> ImageView(context).fromResource(res)
                is SPViewData.SPImageUrlData -> ImageView(context).apply {
                    context.loadImageUrl(url, this)
                }
                is SPViewData.SPrimaryChip -> SPPrimaryChipIconImpl(context).create(this)
                is SPViewData.SPSecondaryChip -> SPSecondaryChipIconImpl(context).create(this)
                is SPViewData.SPDigitalChip -> SPDigitalChipIconImpl(context).create(this)
                is SPViewData.SPEmptyChip -> SPEmptyChipIconImpl(context).create(this)
            }
        }
    }
}

