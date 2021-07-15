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
    fun create(data: SPIconData): View

    sealed class SPIconData {
        data class SPImageResourcesData(@DrawableRes val res: Int) : SPIconData()
        data class SPImageDefaultResourcesData(@DrawableRes val res: Int) : SPIconData()
        data class SPImageUrlData(val url: String) : SPIconData()

        data class SPEmptyChip(val chipStyle: SPEmptyChipStyle) : SPIconData()
        data class SPrimaryChip(val chipSize: SPChipSize) : SPIconData()
        data class SPSecondaryChip(val bankLogoUrl: String, val styleRes: StyleRes) : SPIconData()
        data class SPDigitalChip(val gradient: SPBankCardGradient, val styleRes: StyleRes) :
            SPIconData()

    }
}


class SPCompanionIconFactory(val context: Context) : SPIconFactory {


    override fun create(data: SPIconFactory.SPIconData): View {
        return when (data) {
            is SPIconFactory.SPIconData.SPImageResourcesData -> createImageView(data.res)
            is SPIconFactory.SPIconData.SPImageDefaultResourcesData -> createImageView(data.res)
            is SPIconFactory.SPIconData.SPImageUrlData -> ImageView(context).apply {
                context.loadImageUrl(
                    data.url,
                    this
                )
            }
            is SPIconFactory.SPIconData.SPrimaryChip -> SPPrimaryChipIconImpl(context).create(data)
            is SPIconFactory.SPIconData.SPSecondaryChip -> SPSecondaryChipIconImpl(context).create(
                data
            )
            is SPIconFactory.SPIconData.SPDigitalChip -> SPDigitalChipIconImpl(context).create(data)
            is SPIconFactory.SPIconData.SPEmptyChip -> SPEmptyChipIconImpl(context).create(data)
        }
    }

    private fun createImageView(@DrawableRes res: Int): ImageView = ImageView(context)
        .apply {
            setImageResource(res)
        }

}

