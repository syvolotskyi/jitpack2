package ge.space.ui.components.bank_cards.chip.selectable_chip_item

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import ge.space.extensions.setTextStyle
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpDefaultChipItemLayoutBinding
import ge.space.ui.components.bank_cards.chip.base.SPBaseChipItem
import ge.space.ui.components.bank_cards.data.SPDefaultChipData
import ge.space.ui.util.extension.visibleOrInvisible

/**
 * A default chip item view which is used inside a list
 *
 * @property chipData allows to set a configuration of the chip
 */
class SPDefaultChipItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPBaseChipItem<SpDefaultChipItemLayoutBinding>(context, attrs, defStyleAttr) {

    /**
     * Sets a chip data which is related to a type of the chip and
     * a background if it's needed
     */
    var chipData : SPDefaultChipData = SPDefaultChipData.SPPhysicalChip
        set(value) {
            field = value

            handleData()
        }

    init {
        handleData()
    }

    override fun setTitlesAppearances(styledAttrs: TypedArray) {
        binding.tvTitle.setTextStyle(
            styledAttrs.getResourceId(
                R.styleable.sp_selectable_chip_item_selectableChipItemTitleStyle,
                R.style.h700_default_title
            )
        )
    }

    override fun getViewBinding(): SpDefaultChipItemLayoutBinding =
        SpDefaultChipItemLayoutBinding.inflate(LayoutInflater.from(context), this)

    private fun handleData() {
        setTitle()
        setChipVisibility()
        trySetDigitalBackground()
    }

    private fun setTitle() {
        binding.tvTitle.text = resources.getString(
            getTitleRes()
        )
    }

    private fun setChipVisibility() {
        with(binding) {
            primaryChip.visibleOrInvisible(
                visiblePhysical()
            )
            digitalChip.visibleOrInvisible(
                visibleDigital()
            )
            chipAddIcon.visibleOrInvisible(
                visibleAddIcon()
            )
        }
    }

    private fun trySetDigitalBackground() {
        (chipData as? SPDefaultChipData.SPDigitalChip)?.let { data ->
            binding.digitalChip.cardBackground = data.background
        }
    }

    private fun visiblePhysical() =
        chipData is SPDefaultChipData.SPPhysicalChip

    private fun visibleDigital() =
        chipData is SPDefaultChipData.SPDigitalChip

    private fun visibleAddIcon() =
        chipData is SPDefaultChipData.SPAddIconChip

    private fun getTitleRes() = when(chipData) {
        is SPDefaultChipData.SPPhysicalChip ->
            R.string.title_default_chip_item_physical_title
        is SPDefaultChipData.SPDigitalChip ->
            R.string.title_default_chip_item_digital_title
        is SPDefaultChipData.SPAddIconChip ->
            R.string.title_default_chip_item_add_title
    }
}