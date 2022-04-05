package ge.space.ui.components.bank_cards.card_list

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import androidx.core.view.isInvisible
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpDefaultChipItemLayoutBinding
import ge.space.ui.components.bank_cards.card_list.base.SPBaseCardList
import ge.space.ui.components.bank_cards.data.SPDefaultChipData
import ge.space.ui.util.extension.setTextStyle

/**
 * A default chip item view which is used inside a list
 *
 * @property chipData allows to set a configuration of the chip
 */
class SPDefaultChipItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPChip_SPSelectableChipItem_Default
) : SPBaseCardList<SpDefaultChipItemLayoutBinding>(context, attrs, defStyleAttr, defStyleRes) {

    /**
     * Sets a chip data which is related to a type of the chip and
     * a background if it's needed
     */
    var chipData: SPDefaultChipData = SPDefaultChipData.SPPhysicalChip
        set(value) {
            field = value

            handleChipData()
        }

    init {
        handleChipData()
    }

    /**
     * Sets text appearances for all titles
     */
    override fun setTitlesAppearances(styledAttrs: TypedArray) {
        binding.tvTitle.setTextStyle(
            styledAttrs.getResourceId(
                R.styleable.sp_selectable_chip_item_selectableChipItemTitleStyle,
                R.style.h700_default_title
            )
        )
    }

    override fun getViewBinding(): SpDefaultChipItemLayoutBinding =
        SpDefaultChipItemLayoutBinding.inflate(
            LayoutInflater.from(context).cloneInContext(
                ContextThemeWrapper(context, R.style.SPBankCardView_ChipDigital)
            ), this
        )

    /**
     * Handle  a chip chip data depends on chipData
     * setting title, visibility and digital background
     */
    private fun handleChipData() {
        setTitle()
        setChipVisibility()
        (chipData as? SPDefaultChipData.SPDigitalChip)?.let {

            binding.digitalChip.setViewStyle(R.style.SPBankCardView_ChipDigital)
            it.setDigitalBackground()
        }
    }

    /**
     * Sets an item title
     */
    private fun setTitle() {

        binding.tvTitle.text = resources.getString(
            getTitleRes()
        )
    }

    private fun setChipVisibility() {
        with(binding) {
            primaryChip.isInvisible = !visiblePhysical()
            chipAddIcon.isInvisible = !visibleAddIcon()
            digitalChip.isInvisible = !visibleDigital()
        }
    }

    private fun SPDefaultChipData.SPDigitalChip.setDigitalBackground() {
        binding.digitalChip.setViewStyle(R.style.SPBankCardView_ChipDigital)
        binding.digitalChip.cardBackground = background
    }

    private fun visiblePhysical() =
        chipData is SPDefaultChipData.SPPhysicalChip

    private fun visibleDigital() =
        chipData is SPDefaultChipData.SPDigitalChip

    private fun visibleAddIcon() =
        chipData is SPDefaultChipData.SPAddIconChip

    private fun getTitleRes() = when (chipData) {
        is SPDefaultChipData.SPPhysicalChip ->
            R.string.title_default_chip_item_physical_title
        is SPDefaultChipData.SPDigitalChip ->
            R.string.title_default_chip_item_digital_title
        is SPDefaultChipData.SPAddIconChip ->
            R.string.title_default_chip_item_add_title
    }
}