package ge.space.ui.components.image

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import ge.space.ui.components.bank_cards.chip.card.factory.SPDigitalChipIconImpl
import ge.space.ui.components.bank_cards.chip.card.factory.SPEmptyChipIconImpl
import ge.space.ui.components.bank_cards.chip.card.factory.SPPrimaryChipIconImpl
import ge.space.ui.components.bank_cards.chip.card.factory.SPSecondaryChipIconImpl
import ge.space.ui.components.bank_cards.data.SPBankCardGradient
import ge.space.ui.components.bank_cards.data.SPChipSize
import ge.space.ui.components.bank_cards.data.SPEmptyChipStyle
import ge.space.ui.util.extension.loadImageUrl

interface SPIconFactory {

    /**
     * Returns a view object.
     *
     */
    sealed class SPIconData {
        data class SPImageResourcesData(@DrawableRes val res: Int) : SPIconData()
        data class SPImageDefaultResourcesData(@DrawableRes val res: Int) : SPIconData()
        data class SPImageUrlData(val url: String) : SPIconData()

        data class SPEmptyChip(val chipStyle: SPEmptyChipStyle) : SPIconData()
        data class SPrimaryChip(val chipSize: SPChipSize) : SPIconData()
        data class SPSecondaryChip(val bankLogoUrl: String, @StyleRes val styleRes: Int) :
            SPIconData()

        data class SPDigitalChip(val gradient: SPBankCardGradient, @StyleRes val styleRes: Int) :
            SPIconData()
    }

    companion object {
        fun SPIconData.createView(context: Context): View {
            return when (this) {
                is SPIconData.SPImageResourcesData -> createView(context, res)
                is SPIconData.SPImageDefaultResourcesData -> createView(context, res)
                is SPIconData.SPImageUrlData -> ImageView(context).apply {
                    context.loadImageUrl(url, this)
                }
                is SPIconData.SPrimaryChip -> SPPrimaryChipIconImpl(context).create(this)
                is SPIconData.SPSecondaryChip -> SPSecondaryChipIconImpl(context).create(this)
                is SPIconData.SPDigitalChip -> SPDigitalChipIconImpl(context).create(this)
                is SPIconData.SPEmptyChip -> SPEmptyChipIconImpl(context).create(this)
            }
        }

        private fun createView(context: Context, @DrawableRes res: Int): ImageView =
            ImageView(context)
                .apply {
                    setImageResource(res)
                }
    }
}

